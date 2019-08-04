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

import br.edson.model.ResponsavelRetirada;

public class GerarListaPDFResponsaveis {
	public static void gerarListaPDFResponsaveis(List<ResponsavelRetirada> listaResponsaveis ) {
	
		Document doc = new Document();
		String arquivoPDF = "Lista de Responsaveis";
		
		try {
			PdfWriter.getInstance(doc, new FileOutputStream(arquivoPDF));
			doc.open();
			
			Paragraph p = new Paragraph("Lista de Responsáveis");
			p.setAlignment(1);
			
			doc.add(p);
			p = new Paragraph(" ");
			doc.add(p);
			
			PdfPTable table = new PdfPTable(2);
			PdfPCell cell = new PdfPCell( new Phrase("Nome"));
			PdfPCell cell1 = new PdfPCell( new Phrase("Código"));
			table.addCell(cell);
			table.addCell(cell1);
			
			for (int i = 0; i < listaResponsaveis.size(); i++) {
				cell = new PdfPCell( new Phrase(listaResponsaveis.get(i).getNome()));
				cell1 = new PdfPCell( new Phrase(Integer.toString( listaResponsaveis.get(i).getCodigo())));
				
				table.addCell(cell);
				table.addCell(cell1);
				
			}
			doc.add(table);
			doc.close();
			try {
				Desktop.getDesktop().open( new File(arquivoPDF));
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
