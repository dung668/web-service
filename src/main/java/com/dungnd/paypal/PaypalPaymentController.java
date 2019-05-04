package com.dungnd.paypal;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.paypal.api.payments.Amount;
import com.paypal.api.payments.Details;
import com.paypal.api.payments.Item;
import com.paypal.api.payments.ItemList;
import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payer;
import com.paypal.api.payments.Payment;
import com.paypal.api.payments.RedirectUrls;
import com.paypal.api.payments.Transaction;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.PayPalRESTException;

import edu.ptit.model.Order;

@WebServlet("/paypal-payment")
public class PaypalPaymentController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private String clientId = "AWn68OkOFBHeT-vw41Dxlj6eT3RIgEPraTrO5yGxnX7GcvzbjnovkPbbBCxWO-TB7aKWswJ36FL0oMxe";
	
	private String clientSecret = "EMtpCKU2HUerQ0OUctwPOTVTWoOXJytIVvQn5jnLPQxPF-OTTrekQlL_FKhcKB-J9r9R69XMoPnECr3c";
	
    public PaypalPaymentController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// logic ---------
		
		// tinh so luong product trong cart
		Order order = null;
		HttpSession session = request.getSession();
		if(session.getAttribute("order") != null)
			order = (Order) session.getAttribute("order");
		else {
			request.getRequestDispatcher("client/jsp/cart.jsp").forward(request, response);
			return;
		}
		double price = 0;
		

        ItemList itemList = new ItemList();
        
        List<Item> items = new ArrayList<Item>();
		
		for(edu.ptit.model.Item item : order.getItems()) {
			price += item.getQuantity() * item.getPrice();
			items.add(new Item(item.getProduct().getName(), String.valueOf(item.getQuantity()), String.valueOf(item.getPrice()), "USD"));
		}
		
		itemList.setItems(items);
		
		// -------
		
		// Details
		Details details = new Details();
		details.setFee("0.5");
		details.setShipping("2.5");
		details.setTax("1.11");
		details.setSubtotal(String.valueOf(price));
		
		Amount amount = new Amount();
        amount.setCurrency("USD");
        amount.setDetails(details);
        amount.setTotal(String.valueOf(price + 3.61)); // 3.61 = 2.5 + 1.11 in details not contains fee
        Transaction transaction = new Transaction();
        transaction.setAmount(amount);
        transaction.setDescription("Eshop - Thanh toán hóa đơn.");
        
        transaction.setItemList(itemList);
        List<Transaction> transactions = new ArrayList<Transaction>();
        transactions.add(transaction);

        Payer payer = new Payer();
        payer.setPaymentMethod("paypal");

        Payment payment = new Payment();
        payment.setIntent("sale");
        payment.setPayer(payer);
        payment.setTransactions(transactions);

        RedirectUrls redirectUrls = new RedirectUrls();
        redirectUrls.setCancelUrl("https://localhost:8443/eshop/cancel");
        redirectUrls.setReturnUrl("https://localhost:8443/eshop/complete");
        payment.setRedirectUrls(redirectUrls);
        Payment createdPayment;
        try {
            String redirectUrl = "";
            APIContext context = new APIContext(clientId, clientSecret, "sandbox");
            createdPayment = payment.create(context);
            if(createdPayment!=null){
                List<Links> links = createdPayment.getLinks();
                for (Links link:links) {
                    if(link.getRel().equals("approval_url")){
                        redirectUrl = link.getHref();
                        break;
                    }
                }
                response.sendRedirect(redirectUrl);
            }
        } catch (PayPalRESTException e) {
            System.out.println("Error happened during payment creation!");
            e.printStackTrace();
        }
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
