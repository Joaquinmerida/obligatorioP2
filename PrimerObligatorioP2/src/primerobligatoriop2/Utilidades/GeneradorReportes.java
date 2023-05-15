package primerobligatoriop2.Utilidades;

import com.itextpdf.text.Chunk;
import java.io.FileOutputStream;
import com.itextpdf.text.Document;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Element;
import com.itextpdf.text.FontFactory;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.Font;

public class GeneradorReportes {

    public static void GenerarPDF(ArrayList<Perfiles> personas) {
        try {
            Document document = new Document(PageSize.A4, 30, 30, 30, 30);
            PdfWriter.getInstance(document, new FileOutputStream("ReporteDeJuegos.pdf"));
            document.open();
            Font fontEncabezado = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 15);
            Font fontContenido = FontFactory.getFont(FontFactory.HELVETICA, 13);
            Paragraph titulo = new Paragraph("Reporte de Juego", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18));
            titulo.setAlignment(Element.ALIGN_CENTER);
            titulo.setSpacingAfter(10);
            document.add(titulo);
            PdfPTable table = new PdfPTable(3);
            table.setWidthPercentage(100);
            float[] columnWidths = {2f, 2f, 1f};
            table.setWidths(columnWidths);
            addTableCell(table, "NOMBRE", fontEncabezado);
            addTableCell(table, "ALIAS", fontEncabezado);
            addTableCell(table, "PARTIDAS JUGADAS", fontEncabezado);
            if (!personas.isEmpty()) {
                Collections.sort(personas, new Comparator<Perfiles>() {
                    @Override
                    public int compare(Perfiles p1, Perfiles p2) {
                        return p1.getNombre().compareTo(p2.getNombre());
                    }
                });
                for (int i = 0; i < personas.size(); i++) {
                    addTableCell(table, personas.get(i).getNombre(), fontContenido);
                    addTableCell(table, personas.get(i).getAlias(), fontContenido);
                    addTableCell(table, personas.get(i).getContadorPartidas() + "", fontContenido);
                }
                document.add(table);
            } else {
                Paragraph texto = new Paragraph("No hay ningun jugador registrado", FontFactory.getFont(FontFactory.HELVETICA, 13));
                texto.setAlignment(Element.ALIGN_CENTER);
                document.add(texto);
            }
            document.close();
            System.out.println("El archivo PDF se ha creado correctamente.");
        } catch (Exception e) {
            System.out.println("No se pudo crear el reporte PDF");
        }
    }

    private static void addTableCell(PdfPTable table, String text, Font font) {
        PdfPCell cell = new PdfPCell(new Paragraph(text, font));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);
    }
}
