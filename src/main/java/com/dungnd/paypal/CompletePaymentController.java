package com.dungnd.paypal;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.paypal.api.payments.Payment;
import com.paypal.api.payments.PaymentExecution;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.PayPalRESTException;

@WebServlet("/complete")
public class CompletePaymentController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private String clientId = "AWn68OkOFBHeT-vw41Dxlj6eT3RIgEPraTrO5yGxnX7GcvzbjnovkPbbBCxWO-TB7aKWswJ36FL0oMxe";
	
	private String clientSecret = "EMtpCKU2HUerQ0OUctwPOTVTWoOXJytIVvQn5jnLPQxPF-OTTrekQlL_FKhcKB-J9r9R69XMoPnECr3c";
	
    public CompletePaymentController() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
            Payment payment = new Payment();
            payment.setId(request.getParameter("paymentId"));
            PaymentExecution paymentExecution = new PaymentExecution();
            paymentExecution.setPayerId(request.getParameter("PayerID"));
            try {
                APIContext context = new APIContext(clientId, clientSecret, "sandbox");
                Payment createdPayment = payment.execute(context, paymentExecution);
                if(createdPayment!=null){
                    request.setAttribute("status", "success");
                    request.setAttribute("payment", createdPayment);
                    
                    System.out.println(createdPayment);
                    
                    System.out.println("pay ok -------------");
                }
            } catch (PayPalRESTException e) {
                System.err.println(e.getDetails());
            }
            
            response.sendRedirect("order");
    
    }

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
