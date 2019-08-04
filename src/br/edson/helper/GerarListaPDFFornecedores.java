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

import br.edson.model.Fornecedor;

public class GerarListaPDFFornecedores {
	
	public static void gerarListaPDFFornecedores( List<Fornecedor> listaFornecedores, boolean tipoPessoa) {// true pessoa física, false pessoa juridica
		Document doc = new Document();
		
		String arquivoPDF = "Lista Fornecedores";
		
		try {
			PdfWriter.getInstance(doc, new FileOutputStream(arquivoPDF));
			doc.open();
			Paragraph p = new Paragraph(" Lista de Fornecedores");
			p.setAlignment(1);
			doc.add(p);
			p = new Paragraph(" ");
			doc.add(p);
			
			PdfPTable table = new PdfPTable(4);
			
			PdfPCell cell = new PdfPCell(new Phrase("Nome"));
			PdfPCell cell1 = new PdfPCell( new Phrase("Telefone"));
			PdfPCell cell2 = new PdfPCell( new Phrase("Email"));
			PdfPCell cell3;
			if(tipoPessoa)
				cell3 = new PdfPCell( new Phrase("CPF"));
			else
				cell3 = new PdfPCell( new Phrase("CNPJ"));
			table.addCell(cell);
			table.addCell(cell1);
			table.addCell(cell2);
			table.addCell(cell3);
			for (int i = 0; i < listaFornecedores.size(); i++) {
				cell = new PdfPCell( new Phrase( listaFornecedores.get(i).getNome()));
				cell1 = new PdfPCell( new Phrase(Long.toString(listaFornecedores.get(i).getFone())));
				cell2 = new PdfPCell( new Phrase(listaFornecedores.get(i).getEmail()));
				cell3 = new PdfPCell( new Phrase(Long.toString(listaFornecedores.get(i).getIdentificacaoPessoa())));
				table.addCell(cell);
				table.addCell(cell1);
				table.addCell(cell2);
				table.addCell(cell3);
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
			JOptionPane.showMessageDialog(null, e.getMessage() + "\nFeche o PDF aberto.");
		}
	}

}
