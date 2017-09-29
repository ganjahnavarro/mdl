package core.reports;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.Format;

import org.springframework.core.env.Environment;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfWriter;

public abstract class ReportGenerator {
	
	private ReportData[] values;
	
	protected final Font TR18B = new Font(Font.FontFamily.TIMES_ROMAN, 18, Font.BOLD);
	protected final Font TR10B = new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.BOLD);
	protected final Font TR9B = new Font(Font.FontFamily.TIMES_ROMAN, 9, Font.BOLD);
	
	protected final Font TR10 = new Font(Font.FontFamily.TIMES_ROMAN, 10);
	protected final Font TR9 = new Font(Font.FontFamily.TIMES_ROMAN, 9);
	protected final Font TR8 = new Font(Font.FontFamily.TIMES_ROMAN, 8);
	
	protected final Format AMOUNT_FORMATTER = new DecimalFormat("#,##0.00"); 
	
	protected abstract void addBody(PdfWriter writer, Document document) throws DocumentException;
	protected abstract Environment getEnvironment();

	public void addContent(PdfWriter writer, Document document) throws DocumentException {
		addHeader(writer, document);
		addBody(writer, document);
		addFooter(writer, document);
	}
	
	protected void addHeader(PdfWriter writer, Document document) throws DocumentException {
		Paragraph preface = new Paragraph();

        preface.add(new Paragraph(getProperty("company.name").toUpperCase(), TR18B));
        preface.add(new Paragraph(getProperty("company.description"), TR9));
        preface.add(new Paragraph(getProperty("company.address"), TR9));
        addEmptyLine(preface, 1);
        
        String contactNo = "Telefax: " + getProperty("company.fax.no") + " - Cell.: " + getProperty("company.mobile.no");
        preface.add(new Paragraph(contactNo, TR9));
        addEmptyLine(preface, 1);
        
        preface.setAlignment(Element.ALIGN_CENTER);
        preface.setLeading(0, 1);
        document.add(preface);
	}
	
	protected void addFooter(PdfWriter writer, Document document) {
		
	}
	
	protected PdfPCell createCell(Object value) {
		return createCell(value, TR10);
	}
	
	protected PdfPCell createCellHeader(Object value) {
		PdfPCell cell = createCell(value, TR10B);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		return cell;
	}
	
	protected PdfPCell createCell(Object value, Font font) {
		Boolean isAmount = value instanceof BigDecimal;
		
		if (isAmount) {
			value = AMOUNT_FORMATTER.format(value);
		}
		
		Paragraph paragraph = new Paragraph(nullSafe(value), font);
		PdfPCell cell = new PdfPCell(paragraph);
		
		if (isAmount) {
			cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		}
		
		cell.setPaddingTop(2);
		cell.setPaddingRight(5);
		cell.setPaddingBottom(6);
		cell.setPaddingLeft(5);
		
		return cell;
	}
	
	protected String nullSafe(Object value) {
		return value != null ? String.valueOf(value) : "";
	}
	
    protected static void addEmptyLine(Paragraph paragraph, int number) {
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
