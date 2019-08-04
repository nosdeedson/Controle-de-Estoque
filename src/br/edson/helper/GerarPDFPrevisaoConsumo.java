package br.edson.helper;

import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import javax.swing.JOptionPane;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import br.edson.model.PrevisaoConsumo;

public class GerarPDFPrevisaoConsumo {
	
	public static void gerarPDFPrecisaoConsumo( List<PrevisaoConsumo> previsaoConsumo, int qtdMeses) {
		Document doc = new Document();
		
		String arquivoPDF = " Previsao consumo";
		
		try {
			PdfWriter.getInstance(doc, new FileOutputStream(arquivoPDF));
			doc.open();
			Paragraph p = new Paragraph("Previsao de consumo para os próximos " + qtdMeses + " meses");
			p.setAlignment(1);
			doc.add(p);
			p = new Paragraph(" ");
			doc.add(p);
			
			PdfPTable table = new PdfPTable(2);
			
			PdfPCell cell = new PdfPCell( new Phrase("Produto"));
			PdfPCell cell1 = new PdfPCell(new Phrase("Consumo"));
			
			table.addCell(cell);
			table.addCell(cell1);
			
			for (int i = 0; i < previsaoConsumo.size(); i++) {
				cell = new PdfPCell( new Phrase(previsaoConsumo.get(i).getNomeProduto()));
				cell1 = new PdfPCell( new Phrase(Integer.toString(previsaoConsumo.get(i).getQuantidade())));
				table.addCell(cell);
				table.addCell(cell1);
			}
			doc.add(table);
			doc.close();
			try {
				Desktop.getDesktop().open(new File(arquivoPDF));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException | DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, e.getMessage() + "\n Feche o PDF aberto.");
		}
	}
}
