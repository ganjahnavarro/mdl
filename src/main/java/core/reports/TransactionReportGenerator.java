package core.reports;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

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

}
