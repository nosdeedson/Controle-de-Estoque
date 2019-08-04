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

import br.edson.model.RelatorioDevolucao;

public class GerarRelatorioDevolucao {
	
	public static void gerarRelatorioDevolucao( List<RelatorioDevolucao> ret) {
		
		Document doc = new Document();
		String arquivopdf= " Lista Devolução";
		try {
			PdfWriter.getInstance(doc, new FileOutputStream(arquivopdf));
			doc.open();
			Paragraph p = new Paragraph("Lista Devolução");
			p.setAlignment(1);
			doc.add(p);
			PdfPTable table = new PdfPTable(3);
			
			PdfPCell cell = new PdfPCell(new Phrase("Responsável Retirada"));
			PdfPCell cell1 = new PdfPCell( new Phrase("Produto Retirado"));
			PdfPCell cell2 = new PdfPCell( new Phrase("Quantidade"));
			table.addCell(cell);
			table.addCell(cell1);
			table.addCell(cell2);
			for (int i = 0; i < ret.size(); i++) {
				cell = new PdfPCell(new Phrase(ret.get(i).getNomeResponsavel()));
				cell1 = new PdfPCell(new Phrase(ret.get(i).getNomeProduto()));
				cell2 = new PdfPCell( new Phrase(Integer.toString(ret.get(i).getQuantidade())));
				table.addCell(cell);
				table.addCell(cell1);
				table.addCell(cell2);
			}
			doc.add(table);
			doc.close();
			try {
				Desktop.getDesktop().open(new File(arquivopdf));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException | DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, e.getMessage() + "\nFeche o PDF aberto.");
		}
	}
}
