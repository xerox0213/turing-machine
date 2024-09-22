package g61453.atl.view.javafx.components;

import g61453.atl.view.javafx.TMColors;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class CommandButton extends Button {
    private final Background defaultBg;
    private final Background hoverBg;

    public CommandButton(String s, TMColors color) {
        super(s);
        CornerRadii cornerRadii = new CornerRadii(5);
        Insets padding = new Insets(10, 20, 10, 20);
        defaultBg = new Background(new BackgroundFill(color.getLight(), cornerRadii, null));
        hoverBg = new Background(new BackgroundFill(color.getDark(), cornerRadii, null));
        this.setBorder(new Border(new BorderStroke(color.getDark(), BorderStrokeStyle.SOLID, cornerRadii, BorderWidths.DEFAULT)));
        this.setBackground(defaultBg);
        this.setPadding(padding);
        this.setTextFill(Color.WHITE);
        this.setCursor(Cursor.HAND);
        this.setFont(Font.font(null, FontWeight.EXTRA_BOLD, 15));
        this.setOnMouseEntered(e -> setBackground(hoverBg));
        this.setOnMouseExited(e -> setBackground(defaultBg));
    }
}
