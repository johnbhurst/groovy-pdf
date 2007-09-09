//CustomPageSize.groovy
//
// @author jwill
import be.jameswilliams.PDFBuilder
import com.lowagie.text.pdf.*
import com.lowagie.text.PageSize
import com.lowagie.text.Rectangle
import java.awt.Color
import com.lowagie.text.Document
import com.lowagie.text.DocumentException
import com.lowagie.text.Paragraph
import com.lowagie.text.PageSize
import com.lowagie.text.pdf.PdfWriter
import com.lowagie.text.Chunk
import com.lowagie.text.Font
import com.lowagie.text.FontFactory
import com.lowagie.text.Phrase
import com.lowagie.text.Image


try {
	def builder = new PDFBuilder()
	def file = 'examples' + File.separator + 'output' + File.separator + 'CustomPageSize.pdf'
	def rect = new Rectangle(216, 720)
	rect.setBackgroundColor(new Color(0xFF, 0xFF, 0xDE))
	def a = builder.document(filename:file, pageSize:rect ){
		paragraph(text:'The size of this page is 216x720 points.')
		paragraph(text:'216pt / 72 points per inch = 3 inch')	
		paragraph(text:'720pt / 72 points per inch = 10 inch')	
		paragraph(text:'The size of this page is 3x10 inch.')	
		paragraph(text:'3 inch x 2.54 = 7.62 cm')	
		paragraph(text:'10 inch x 2.54 = 25.4 cm')	
		paragraph(text:'The size of this page is 7.62x25.4 cm.')	
		paragraph(text:'The backgroundcolor of the Rectangle used for this PageSize, is #FFFFDE.')	
		paragraph(text:"That's why the background of this document is yellowish...")	
	}
} catch (Exception e) {
	e.printStackTrace()
}

/*System.out.println("Custom PageSize and backgroundcolor");
        
        // step 1: creation of a document-object
        Rectangle pageSize = new Rectangle(216, 720);
        pageSize.setBackgroundColor(new java.awt.Color(0xFF, 0xFF, 0xDE));
        Document document = new Document(pageSize);
        
        try {
            
            // step 2:
            // we create a writer that listens to the document
            // and directs a PDF-stream to a file
            
            PdfWriter.getInstance(document, new FileOutputStream("CustomPageSize.pdf"));
            
            // step 3: we open the document
            document.open();
            
            // step 4: we add some paragraphs to the document
            document.add(new Paragraph("The size of this page is 216x720 points."));
            document.add(new Paragraph("216pt / 72 points per inch = 3 inch"));
            document.add(new Paragraph("720pt / 72 points per inch = 10 inch"));
            document.add(new Paragraph("The size of this page is 3x10 inch."));
            document.add(new Paragraph("3 inch x 2.54 = 7.62 cm"));
            document.add(new Paragraph("10 inch x 2.54 = 25.4 cm"));
            document.add(new Paragraph("The size of this page is 7.62x25.4 cm."));
            document.add(new Paragraph("The backgroundcolor of the Rectangle used for this PageSize, is #FFFFDE."));
            document.add(new Paragraph("That's why the background of this document is yellowish..."));
            
        }
        catch(DocumentException de) {
            System.err.println(de.getMessage());
        }
        catch(IOException ioe) {
            System.err.println(ioe.getMessage());
        }
        
        // step 5: we close the document
        document.close();
*/
