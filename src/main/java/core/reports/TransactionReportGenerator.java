package core.reports;

import java.math.BigDecimal;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfWriter;

import core.model.Stock;

public abstract class TransactionReportGenerator extends ReportGenerator {
	
	@Override
	protected void addHeader(PdfWriter writer, Document document) throws DocumentException {
		Paragraph preface = new Paragraph();

        preface.add(new Paragraph(getProperty("company.name").toUpperCase(), FontFactory.C18B));
        preface.add(new Paragraph(getProperty("company.description"), FontFactory.C9));
        preface.add(new Paragraph(getProperty("company.address"), FontFactory.C9));
        addEmptyLine(preface, 1);
        
        String contactNo = "Telefax: " + getProperty("company.fax.no") + " - Cell.: " + getProperty("company.mobile.no");
        preface.add(new Paragraph(contactNo, FontFactory.C9));
        addEmptyLine(preface, 1);
        
        preface.setAlignment(Element.ALIGN_CENTER);
        preface.setLeading(0, 1);
        document.add(preface);
	}
	
	protected PdfPCell createStockCell(Stock stock) {
		String stockRowA = stock.getName()
				+ (stock.getOem() != null ? " / " + stock.getOem() : "")
				+ (stock.getCategory() != null ? " / " + stock.getCategory().getDisplayString() : "");
		String stockRowB = (stock.getDescription() != null ? stock.getDescription() : "")
				+ (stock.getBrand() != null ? " / " + stock.getBrand().getDisplayString() : "");
		
		Paragraph paragraph = new Paragraph();
		Paragraph paragraphA = new Paragraph(stockRowA, FontFactory.C9);
		Paragraph paragraphB = new Paragraph("    " + stockRowB, FontFactory.C9);
		
		paragraph.add(paragraphA);
		paragraph.add(paragraphB);
		
		PdfPCell cell = new PdfPCell(paragraph);
		cell.setBorder(Rectangle.LEFT | Rectangle.RIGHT);
		addCellPadding(cell);
		return cell;
	}
	
	protected PdfPCell createDiscountCell(BigDecimal discount1, BigDecimal discount2) {
		int discountCount = (discount1 != null ? 1 : 0) + (discount2 != null ? 2 : 0);
		String discount = (discount1 != null ? DISCOUNT_FORMATTER.format(discount1) + "%" : "")
				+ (discountCount > 1 ? "-" : "")
				+ (discount2 != null ? DISCOUNT_FORMATTER.format(discount2) + "%" : "");

		PdfPCell cell = createCell(discountCount > 0  ? discount : "NET");
		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell.setBorder(Rectangle.LEFT | Rectangle.RIGHT);
		return cell;
	}

}
