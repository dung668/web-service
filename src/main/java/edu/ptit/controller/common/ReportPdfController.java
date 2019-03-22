package edu.ptit.controller.common;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import edu.ptit.dao.OrderDAO;
import edu.ptit.dao.impl.OrderDAOImpl;
import edu.ptit.model.Item;

@WebServlet({"/report-pdf","/admin/report-pdf"})
public class ReportPdfController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ReportPdfController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/pdf");
		response.addHeader("Content-Disposition", "attachment; filename=" + "order.pdf");
		
		OutputStream os = response.getOutputStream();
		
		Document document = new Document();
		
		
		
		try {
			BaseFont bf = BaseFont.createFont("c:/windows/fonts/ARIAL.TTF", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);

			Font font = new Font(bf,20f,Font.BOLD,BaseColor.BLUE);
			Font fontHeader = new Font(bf,14f,Font.BOLD,BaseColor.DARK_GRAY);
			Font fontContent = new Font(bf,14f,Font.NORMAL,BaseColor.DARK_GRAY);
			
			PdfWriter.getInstance(document, os);
			
			document.open();

			Paragraph paragraph = new Paragraph();		

			paragraph.add(new Phrase("Chi tiết hóa đơn", font));
			paragraph.setAlignment(Element.ALIGN_CENTER);
			
			paragraph.add(new Phrase(Chunk.NEWLINE));
			paragraph.add(new Phrase(Chunk.NEWLINE));
			
			document.add(paragraph);
			
			
			PdfPTable table = new PdfPTable(new float[] { 8,52,8,20,12 });
			//table.setPaddingTop(20);
			PdfPCell cell1 = new PdfPCell(new Paragraph("ID",fontHeader));
			PdfPCell cell2 = new PdfPCell(new Paragraph("Tên sản phẩm",fontHeader));
			PdfPCell cell3 = new PdfPCell(new Paragraph("SL",fontHeader));
			PdfPCell cell4 = new PdfPCell(new Paragraph("Giá bán",fontHeader));
			PdfPCell cell5 = new PdfPCell(new Paragraph("Mã HĐ",fontHeader));
			
			table.addCell(cell1);
			table.addCell(cell2);
			table.addCell(cell3);
			table.addCell(cell4);
			table.addCell(cell5);
			
			/////////////////////////////////
			
			int id = Integer.parseInt(request.getParameter("id")); // id cua order
			
			OrderDAO orderDao = new OrderDAOImpl();
			
			List<Item> items = orderDao.getOrderByOrderId(id);
			//request.setAttribute("items", items);
			
			double price = 0D;
			int quantity = 0;
			for(Item item : items) {
				price += item.getPrice() * item.getQuantity();
				quantity += item.getQuantity();
			}			
			
			/////////////////////////////////
			
			int i = 1; // con chạy cho id item
			for(Item item : items) {
				
				cell1 = new PdfPCell(new Paragraph(String.valueOf(i++),fontContent));
				cell2 = new PdfPCell(new Paragraph(item.getProduct().getName(),fontContent));
				cell3 = new PdfPCell(new Paragraph(String.valueOf(item.getQuantity()),fontContent));
				cell4 = new PdfPCell(new Paragraph("" + ((int) item.getPrice()) + "VNĐ",fontContent));
				cell5 = new PdfPCell(new Paragraph(""+id,fontContent));

				// them vao bang
				table.addCell(cell1);
				table.addCell(cell2);
				table.addCell(cell3);
				table.addCell(cell4);
				table.addCell(cell5);
			}
			
			Paragraph p = new Paragraph();
			Font f = new Font(bf);
			
			p.add(new Phrase("Tổng số sản phẩm: " + quantity + " | Tổng số tiền: " + (int) price + " VNĐ",f));
			p.setAlignment(Element.ALIGN_CENTER);
			
			document.add(table);
			document.add(p);
			document.close();
			
			System.out.println("in pdf");
			
			
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		
	}
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
