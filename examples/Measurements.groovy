//Measurements.groovy
//
// @author jwill
import be.jameswilliams.pdf.PDFBuilder
import com.lowagie.text.pdf.*
import com.lowagie.text.PageSize
import com.lowagie.text.Rectangle


try {
	def builder = new PDFBuilder()
	def file = 'examples' + File.separator + 'output' + File.separator + 'Measurements.pdf'
	def a = builder.document(filename:file,pageSize:new Rectangle(288, 720), marginLeft:36, marginRight:18, marginTop:72, marginBottom:72){
			paragraph(text:"The size of this page is 288x720 points.")
            paragraph(text:"288pt / 72 points per inch = 4 inch")
            paragraph(text:"720pt / 72 points per inch = 10 inch")
            paragraph(text:"The size of this page is 4x10 inch.")
            paragraph(text:"4 inch x 2.54 = 10.16 cm")
            paragraph(text:"10 inch x 2.54 = 25.4 cm")
            paragraph(text:"The size of this page is 10.16x25.4 cm.")
            paragraph(text:"The left border is 36pt or 0.5 inch or 1.27 cm")
            paragraph(text:"The right border is 18pt or 0.25 inch or 0.63 cm.")
            paragraph(text:"The top and bottom border are 72pt or 1 inch or 2.54 cm.")
	}
} catch (Exception e) {
	e.printStackTrace()
}
