package Utilities;

import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;

import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.kernel.pdf.canvas.draw.DashedLine;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.IElement;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.LineSeparator;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.HorizontalAlignment;
import com.itextpdf.layout.properties.Property;
import com.itextpdf.layout.properties.TextAlignment;

import Data.MenuItem;
import Data.Order;
import Data.OrderController;
import Data.OrderMenuItem;
import Data.Payment;
import UI.MainWindow;

public class Receipt {

	public static void generateReceipt() {

		Order order = OrderController.getOrder();
		String path = order.getOrderNo() + ".pdf";
		int pageHeight = 410;

		try {
			ImageData data = ImageDataFactory.create("img\\Logo.png");
			Image img = new Image(data);
			img.scaleToFit(200, 70);
			img.setHorizontalAlignment(HorizontalAlignment.CENTER);
			
			PdfWriter pdfWriter = new PdfWriter(path);

			PdfDocument pdfDocument = new PdfDocument(pdfWriter);
			
			for(int i = 0; i < order.getItems().size() + order.getPayments().size(); i++) {
				pageHeight += 17;
			}
			
			pdfDocument.addNewPage(new PageSize(235, pageHeight));

			Document document = new Document(pdfDocument);
			document.setMargins(20, 10, 0, 10);
			document.add(img);

			Paragraph para = new Paragraph("ASL");
			para.setFontSize(20).setTextAlignment(TextAlignment.CENTER).setMultipliedLeading(0.5f);
			document.add(para);
			Paragraph para2 = new Paragraph();
			para2.setFixedLeading(10).setFontSize(8).add("Plot-21, Gulshan Avenue, Gulshan-1,").add("\r\n")
					.add("Dhaka-1213, Bangladesh\r\n").add("Phone# 01711275323")
					.setTextAlignment(TextAlignment.CENTER);

			document.add(para2);

			CustomDashedLine d = new CustomDashedLine(2f);
			document.add(new LineSeparator(d));
			
			LocalDate date = LocalDate.now();
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			
			LocalTime now = LocalTime.now();
			DateTimeFormatter ttf = DateTimeFormatter.ofPattern("HH:mm:ss");
			
			Paragraph para3 = new Paragraph();
			para3.setFontSize(8).setFixedLeading(10);
			para3.add("Date: " + dtf.format(date) + "\t\t\t\t\t\t\t\t\t\tTime: " + ttf.format(now) +"\r\n");
			para3.add("Order No: " + order.getOrderNo() + "\r\n");
			para3.add("Invoice To: " + (order.getCustomer() != null ? order.getCustomer().getCustomerName() : "") +"\r\n");
			para3.add("Served By: " + MainWindow.account.getUserID());
			
			document.add(para3);
			
			document.add(new LineSeparator(d));
			document.add(new Paragraph().setFixedLeading(5));
			
			float[] columnWidth = {110, 20, 40, 50};
			Table table = new Table(columnWidth);
			
			Cell c_00 = new Cell();
			c_00.add(new Paragraph("Item Name").setFontSize(8));
			Cell c_01 = new Cell();
			c_01.add(new Paragraph("Qty").setFontSize(8));
			Cell c_02 = new Cell();
			c_02.add(new Paragraph("Price").setFontSize(8));
			Cell c_03 = new Cell();
			c_03.add(new Paragraph("Total Price").setFontSize(8));
			
			table.addCell(c_00).addCell(c_01).addCell(c_02).addCell(c_03);
			
			for(OrderMenuItem ordItem : order.getItems()) {
				MenuItem item = ordItem.getItem();
				table.addCell(new Paragraph(item.getItemName()).setFontSize(8));
				table.addCell(new Paragraph(Integer.toString(ordItem.getQuantity())).setFontSize(8));
				table.addCell(new Paragraph(Double.toString(ordItem.getDiscountedPrice())).setFontSize(8));
				table.addCell(new Paragraph(Double.toString(ordItem.getQuantity() * ordItem.getDiscountedPrice())).setFontSize(8));
			}
			
			document.add(table);
			
			document.add(new Paragraph().setFixedLeading(5));
			document.add(new LineSeparator(d));
			document.add(new Paragraph());
			
			float[] columnWidth2 = {110, 100};
			Table table2 = new Table(columnWidth2);

			table2.addCell(new Paragraph("Gross Total :").setFontSize(8).setFirstLineIndent(5));
			table2.addCell(new Paragraph(Double.toString(order.getSubTotal())).setFontSize(8).setTextAlignment(TextAlignment.RIGHT));
			table2.addCell(new Paragraph("Discount @" + (order.getCustomer() == null ? "0.0" : order.getCustomer().getSpecialDiscount() + "% :")).setFontSize(8).setFirstLineIndent(5));
			table2.addCell(new Paragraph(Double.toString(order.getSubTotal()) + "LOL").setFontSize(8).setTextAlignment(TextAlignment.RIGHT));
			
			RemoveBorder(table2);
			document.add(table2);
			document.add(new Paragraph());
			
			Table table3 = new Table(columnWidth2);
			table3.addCell(new Paragraph("Net Total :").setFontSize(8).setFirstLineIndent(5));
			table3.addCell(new Paragraph(Double.toString(order.getTotal())).setFontSize(8).setTextAlignment(TextAlignment.RIGHT));
			
			RemoveBorder(table3);
			document.add(table3);

			document.add(new Paragraph().setFixedLeading(5));
			document.add(new LineSeparator(d));
			
			document.add(new Paragraph("Payments:").setFontSize(8));
			
			Table table6 = new Table(columnWidth2);
			
			for(Payment payment : order.getPayments()) {
				table6.addCell(new Paragraph(payment.getPaymentType()).setFontSize(8));
				table6.addCell(new Paragraph(Double.toString(payment.getAmount())).setFontSize(8).setTextAlignment(TextAlignment.RIGHT));
			}
			
			document.add(table6);
			
			document.add(new Paragraph("Thank you for your order!").setTextAlignment(TextAlignment.CENTER));
						
			document.close();
			System.out.println("PDF created");
			System.gc();
			Desktop.getDesktop().open(new File(path));

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private static void RemoveBorder(Table table)
	{
	    for (IElement iElement : table.getChildren()) {
	    	if(iElement instanceof Cell)
	    		((Cell)iElement).setBorder(Border.NO_BORDER);
	    }
	}

	private static class CustomDashedLine extends DashedLine {
		public CustomDashedLine(float lineWidth) {
			super(lineWidth);
		}

		@Override
		public void draw(PdfCanvas canvas, Rectangle drawArea) {
			canvas.saveState().setLineWidth(getLineWidth()).setStrokeColor(getColor()).setLineDash(4, 2, 5)
					.moveTo(drawArea.getX(), drawArea.getY() + getLineWidth() / 2)
					.lineTo(drawArea.getX() + drawArea.getWidth(), drawArea.getY() + getLineWidth() / 2).stroke()
					.restoreState();
		}
	}
}
