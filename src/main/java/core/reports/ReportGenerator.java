package core.reports;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.core.env.Environment;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.DottedLineSeparator;

public abstract class ReportGenerator {
	
	private ReportData[] values;
	
	protected final Format AMOUNT_FORMATTER = new DecimalFormat("#,##0.00"); 
	protected final DateFormat DATE_FORMATTER = new SimpleDateFormat("MM/dd/yyyy");
	protected final Format DISCOUNT_FORMATTER = new DecimalFormat("#,##0.##");
	
	protected abstract void addBody(PdfWriter writer, Document document) throws DocumentException, ParseException;
	protected abstract Environment getEnvironment();

	public void addContent(PdfWriter writer, Document document) throws DocumentException, ParseException {
		addHeader(writer, document);
		addBody(writer, document);
		addFooter(writer, document);
	}
	
	public Document newDocumentInstance() {
		return new Document(PageSize.A4, 0, 0, 20, 20);
	}
	
	protected void addHeader(PdfWriter writer, Document document) throws DocumentException {
		
	}
	
	protected void addFooter(PdfWriter writer, Document document) {
		
	}
	
	protected PdfPCell createCell(Object value) {
		return createCell(value, FontFactory.C9);
	}
	
	protected PdfPCell createCellHeader(Object value) {
		PdfPCell cell = createCell(value, FontFactory.C10B);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		return cell;
	}
	
	protected PdfPCell createCell(Object value, Font font) {
		Boolean isAmount = value instanceof BigDecimal;
		Boolean isDate = value instanceof Date;
		
		value = isAmount ? AMOUNT_FORMATTER.format(value) : value;
		value = isDate ? DATE_FORMATTER.format(value) : value;
		
		Paragraph paragraph = new Paragraph(nullSafe(value), font);
		PdfPCell cell = new PdfPCell(paragraph);
		
		if (isAmount) {
			cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		}
		
		addCellPadding(cell);
		return cell;
	}
	
	protected void addCellPadding(PdfPCell cell) {
		cell.setPaddingTop(2);
		cell.setPaddingRight(5);
		cell.setPaddingBottom(6);
		cell.setPaddingLeft(5);
	}
	
	protected String nullSafe(Object value) {
		return value != null ? String.valueOf(value) : "";
	}
	
	protected Long parseLong(Object value) {
		return value != null ? Long.valueOf(value.toString()) : null;
	}
	
	protected Date parseDate(Object value) throws ParseException {
		return value != null ? DATE_FORMATTER.parse(value.toString()) : null;
	}
	
	protected void addLineSeparator(Document document) throws DocumentException {
		DottedLineSeparator lineSeparator = new DottedLineSeparator();
		lineSeparator.setLineColor(BaseColor.LIGHT_GRAY);
		lineSeparator.setGap(3);
		document.add(lineSeparator);
	}
	
    protected void addEmptyLine(Paragraph paragraph, int number) {
        for (int i = 0; i < number; i++) {
            paragraph.add(new Paragraph(" "));
        }
    }
	
	public void setValues(ReportData[] values) {
		this.values = values;
	}
	
	protected String getProperty(String key) {
		String value = getEnvironment().getProperty(key);
		return value != null ? value : key;
	}
	
	protected Object getValue(String key) {
		Object value = null;
		if (values != null) {
			for (ReportData reportData : values) {
				if (reportData.getKey().equals(key)) {
					value = reportData.getValue();
					break;
				}
			}
		}
		return value;
	}
	
}
