//Margins.groovy
//
// @author jwill
import be.jameswilliams.pdf.PDFBuilder
import com.lowagie.text.pdf.*
import com.lowagie.text.PageSize
import com.lowagie.text.Rectangle

try {
	def builder = new PDFBuilder()
	def file = 'examples' + File.separator + 'output' + File.separator + 'Margins.pdf'
	def helloSun
	def a = builder.document(filename:file,pageSize:PageSize.A5, margins:[36,72,108,180]){
		paragraph(text:"The left margin of this document is 36pt (0.5 inch); the right margin 72pt (1 inch); the top margin 108pt (1.5 inch); the bottom margin 180pt (2.5 inch). ")
		paragraph(name:"helloSun") {
			(0..20).each {
				chunk(text:"Hello World, Hello Sun, Hello Moon, Hello Stars, Hello Sea, Hello Land, Hello People. ")	
			}
		}
		println "helloSun: "+helloSun
		paragraph(margins:[180, 108, 72, 36], text:"Now we change the margins. You will see the effect on the next page.")
		widget(name:"helloSun")
		paragraph(text:"Starting on the next page, the margins will be mirrored.", marginMirroring:true)
		widget(name:"helloSun")
		widget(name:"helloSun")
		widget(name:"helloSun")
	}
} catch (Exception e) {
	e.printStackTrace()
}
