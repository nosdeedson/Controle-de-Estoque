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

import br.edson.model.RelatorioEntrada;

public class GerarRelatorioEntrada {
	public static void relatorioEntrada(List<RelatorioEntrada> listaEntradaRelatorio) {

		Document doc = new Document();
		String arquivoPDF = " Relação entradas";
		try {
			PdfWriter.getInstance(doc, new FileOutputStream(arquivoPDF));
			doc.open();
			Paragraph p = new Paragraph("Entradas");
			p.setAlignment(1);
			doc.add(p);
			p = new Paragraph(" ");
			doc.add(p);

			PdfPTable table = new PdfPTable(5);

			PdfPCell cel1 = new PdfPCell(new Phrase("Produto"));
			PdfPCell cel2 = new PdfPCell(new Phrase("Fornecedor"));
			PdfPCell cel3 = new PdfPCell(new Phrase("Data Entrada"));
			PdfPCell cel4 = new PdfPCell(new Phrase("Nota Fiscal"));
			PdfPCell cel5 = new PdfPCell(new Phrase("Quantidade"));

			table.addCell(cel1);
			table.addCell(cel2);
			table.addCell(cel3);
			table.addCell(cel4);
			table.addCell(cel5);

			for (int i = 0; i < listaEntradaRelatorio.size(); i++) {
				cel1 = new PdfPCell(new Phrase(listaEntradaRelatorio.get(i).getNomeProduto()));
				cel2 = new PdfPCell(new Phrase(listaEntradaRelatorio.get(i).getNomeFornecedor()));
				cel3 = new PdfPCell(new Phrase(listaEntradaRelatorio.get(i).getDataEntrada()));
				cel4 = new PdfPCell(new Phrase(Integer.toString(listaEntradaRelatorio.get(i).getNumeroNota())));
				cel5 = new PdfPCell(new Phrase(Integer.toString(listaEntradaRelatorio.get(i).getQuantidade())));
				table.addCell(cel1);
				table.addCell(cel2);
				table.addCell(cel3);
				table.addCell(cel4);
				table.addCell(cel5);
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
