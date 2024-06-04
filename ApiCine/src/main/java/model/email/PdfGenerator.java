package model.email;

import model.entities.Entrada;
import model.entities.Pedido;
import model.entities.Producto;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import java.io.IOException;
import java.util.List;

public class PdfGenerator {

    public static String generatePdf(Pedido pedido) throws IOException {
        PDDocument document = new PDDocument();
        PDPage page = new PDPage(PDRectangle.A4);
        document.addPage(page);

        PDPageContentStream contentStream = new PDPageContentStream(document, page);

        float margin = 50; // margin from the edge of the page
        float columnWidth = 150; // width of each column
        float columnHeight = 20; // height of each cell
        float y = page.getMediaBox().getHeight() - margin; // initial y-coordinate

        contentStream.setFont(PDType1Font.TIMES_ROMAN, 16);
        contentStream.beginText();
        contentStream.newLineAtOffset(margin, y);
        contentStream.showText("Detalles del Pedido");
        contentStream.endText();
        y -= columnHeight; // decrement y-coordinate

        // print pedido details
        contentStream.setFont(PDType1Font.TIMES_ROMAN, 12);
        contentStream.beginText();
        contentStream.newLineAtOffset(margin, y);
        contentStream.showText("ID Pedido: " + pedido.getIdPedido());
        y -= columnHeight; // decrement y-coordinate
        contentStream.newLineAtOffset(0, -columnHeight);
        contentStream.showText("Subtotal: " + pedido.getSubtotal());
        y -= columnHeight; // decrement y-coordinate
        contentStream.newLineAtOffset(0, -columnHeight);
        contentStream.showText("Total: " + pedido.getTotal());
        y -= columnHeight; // decrement y-coordinate
        contentStream.newLineAtOffset(0, -columnHeight);
        contentStream.showText("IVA: " + pedido.getIva());
        y -= columnHeight; // decrement y-coordinate
        contentStream.newLineAtOffset(0, -columnHeight);
        contentStream.showText("Fecha: " + pedido.getFecha());
        contentStream.endText();
        y -= columnHeight; // decrement y-coordinate

        // print entrada details
        List<Entrada> entradas = pedido.getEntradas();
        if (entradas != null) {
            contentStream.beginText();
            contentStream.newLineAtOffset(margin, y);
            contentStream.showText("Entradas:");
            contentStream.endText();
            y -= columnHeight; // decrement y-coordinate
            for (Entrada entrada : entradas) {
                contentStream.beginText();
                contentStream.newLineAtOffset(margin, y);
                contentStream.showText("ID Entrada: " + entrada.getIdEntrada() +
                        ", ID Proyeccion: " + entrada.getIdVisualizar() +
                        ", ID Butaca: " + entrada.getIdButaca() +
                        ", Pelicula: " + entrada.getPelicula()
                );
                contentStream.endText();
                y -= columnHeight; // decrement y-coordinate
            }
        }

        // print producto details
        List<Producto> productos = pedido.getProductos();
        if (productos != null) {
            contentStream.beginText();
            contentStream.newLineAtOffset(margin, y);
            contentStream.showText("Productos:");
            contentStream.endText();
            y -= columnHeight; // decrement y-coordinate
            for (Producto producto : productos) {
                contentStream.beginText();
                contentStream.newLineAtOffset(margin, y);
                contentStream.showText("Nombre: " + producto.getNombreProducto() +
                        ", Precio: " + producto.getPrecioUnitario() +
                        ", Cantidad: " + producto.getPedir().getCantidad() +
                        ", Precio Total: " + producto.getPedir().getPrecio());
                contentStream.endText();
                y -= columnHeight; // decrement y-coordinate
            }
        }

        contentStream.close();

        String pdfFilePath = "pedido_" + pedido.getIdPedido() + ".pdf";
        document.save(pdfFilePath);
        document.close();

        return pdfFilePath;
    }
}
