package ui;

import java.time.LocalDate;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import model.TurboMilestone;

public class EditMilestoneDialog extends Dialog<TurboMilestone>{
	
	private TurboMilestone originalMilestone;

	public EditMilestoneDialog(Stage parentStage, TurboMilestone originalMilestone) {
		super(parentStage);
		this.originalMilestone = originalMilestone;
		
		setTitle("Edit Milestone");
		setSize(330, 50);
	}

	@Override
	protected Parent content() {
		TextField milestoneTitleField = new TextField();
		milestoneTitleField.setPrefWidth(120);
		milestoneTitleField.setText(originalMilestone.getTitle());
		
		DatePicker datePicker = (originalMilestone.getDueOn() != null) ?
			new DatePicker(originalMilestone.getDueOn()) : new DatePicker();
		datePicker.setPrefWidth(110);

		Button done = new Button("Done");
		done.setOnAction(e -> {
			respond(milestoneTitleField.getText(), datePicker.getValue());
			close();
		});
		
		HBox layout = new HBox();
		layout.setPadding(new Insets(15));
		layout.setSpacing(10);
		layout.setAlignment(Pos.BASELINE_CENTER);
		layout.getChildren().addAll(milestoneTitleField, datePicker, done);

		return layout;
	}

	private void respond(String title, LocalDate dueDate) {
		originalMilestone.setTitle(title);
		originalMilestone.setDueOn(dueDate);
		completeResponse(originalMilestone);
	}
	
	
	
	
	
}