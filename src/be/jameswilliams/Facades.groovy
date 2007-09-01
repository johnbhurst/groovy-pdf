// Copyright © 2007 James Williams 
//
// Licensed under the Apache License, Version 2.0 (the "License"); you may not 
// use this file except in compliance with the License. You may obtain a copy 
// of the License at 
//
// http://www.apache.org/licenses/LICENSE-2.0 
//
// Unless required by applicable law or agreed to in writing, software 
// distributed under the License is distributed on an "AS IS" BASIS, WITHOUT 
// WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the 
// License for the specific language governing permissions and limitations 
// under the License.
//  Author : James Williams
package be.jameswilliams
import com.lowagie.text.*
import com.lowagie.text.pdf.*
interface Facade {
	def process() { }
}
public class ImageFacade implements Facade{
	String url
	Image image
	def process() {
		if (image == null)
			image = Image.getInstance(url)
		return image
	}
}

public class DirectContentFacade implements Facade{
	PdfContentByte cb = PDFBuilder.writer.getDirectContent()
	String type
	def font
	int fontSize
	def content = []
	void add(AlignedTextFacade a) {
		content.add(a)
	}
	def process() {
		cb.beginText()
		cb.setFontAndSize(font,fontSize)
		for(AlignedTextFacade a in content) {
			cb.showTextAligned(a.align,a.text, a.w,a.h,a.z)
		}
		cb.endText()
	}
}

public class AlignedTextFacade implements Facade{
	int align
	String text
	int w
	int h
	int z
	
	def process() {}
}

public class TableFacade implements Facade{
	def cells = []
	def columns
	def x, y
	float[ ]totalWidth 
	void add(TableCellFacade cell) {
		cells.add(cell)
		println cells.size
	}
	def process() {
		PdfPTable table = new PdfPTable(columns)
		for(TableCellFacade cell in cells) {
			table.addCell(cell.process())
		}
		table.setTotalWidth(totalWidth)
		table.writeSelectedRows(0,-1,x,y,PDFBuilder.writer.getDirectContent())
		return table
	}
}

public class TableCellFacade implements Facade {
	def text
	def horizontalAlignment
	def colSpan
	def font
	
	def process() {
		if (font == null)
			font = new Font(Font.COURIER, Font.DEFAULTSIZE, Font.NORMAL)
		PdfPCell cell = new PdfPCell(new Phrase(text, font))
		if (horizontalAlignment != null)
			cell.setHorizontalAlignment(horizontalAlignment)
		return cell
	}
}
