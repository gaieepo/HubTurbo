package tests;

import backend.resource.TurboMilestone;
import org.junit.BeforeClass;
import org.junit.Test;
import ui.components.pickers.PickerMilestone;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;

public class PickerMilestoneTest {

    private static final String REPO_ID = "test/testrepo";

    private static List<PickerMilestone> listWithNoExistingAndSelected, listWithExisting, listWithSelected,
            listWithMilestonesWithNoDueDates;
    private static PickerMilestone noDueDateMilestone1, noDueDateMilestone2, openMilestone1, openMilestone2,
            closedMilestone, existingMilestone, selectedMilestone;

    @BeforeClass
    public static void initialize() {
        noDueDateMilestone1 = new PickerMilestone(new TurboMilestone(REPO_ID, 1, "noDueDateMilestone1"));

        noDueDateMilestone2 = new PickerMilestone(new TurboMilestone(REPO_ID, 2, "noDueDateMilestone2"));

        openMilestone1 = new PickerMilestone(new TurboMilestone(REPO_ID, 3, "openMilestone1"));
        openMilestone1.setDueDate(Optional.of(LocalDate.now().plusDays(1)));

        openMilestone2 = new PickerMilestone(new TurboMilestone(REPO_ID, 4, "openMilestone2"));
        openMilestone2.setDueDate(Optional.of(LocalDate.now().plusDays(1)));

        closedMilestone = new PickerMilestone(new TurboMilestone(REPO_ID, 5, "closedMilestone"));
        closedMilestone.setOpen(false);

        existingMilestone = new PickerMilestone(new TurboMilestone(REPO_ID, 6, "existingMilestone"));
        existingMilestone.setExisting(true);

        selectedMilestone = new PickerMilestone(new TurboMilestone(REPO_ID, 7, "selectedMilestone"));
        selectedMilestone.setSelected(true);

        listWithNoExistingAndSelected = new ArrayList<>();
        listWithNoExistingAndSelected.add(openMilestone1);
        listWithNoExistingAndSelected.add(openMilestone2);

        listWithExisting = new ArrayList<>();
        listWithExisting.add(existingMilestone);
        listWithExisting.add(openMilestone1);

        listWithSelected = new ArrayList<>();
        listWithSelected.add(selectedMilestone);
        listWithSelected.add(openMilestone1);

        listWithMilestonesWithNoDueDates = new ArrayList<>();
        listWithMilestonesWithNoDueDates.add(noDueDateMilestone1);
        listWithMilestonesWithNoDueDates.add(noDueDateMilestone2);
    }

    @Test
    public void getExistingMilestone_noExistingMilestone_returnEmpty() {
        assertEquals(Optional.empty(), PickerMilestone.getExistingMilestone(listWithNoExistingAndSelected));
    }

    @Test
    public void getExistingMilestone_haveExistingMilestone_returnExistingMilestone() {
        assertEquals(Optional.of(existingMilestone), PickerMilestone.getExistingMilestone(listWithExisting));
    }

    @Test
    public void getSelectedMilestone_noSelectedMilestone_returnEmpty() {
        assertEquals(Optional.empty(), PickerMilestone.getSelectedMilestone(listWithNoExistingAndSelected));
    }

    @Test
    public void getSelectedMilestone_haveSelectedMilestone_returnSelectedMilestone() {
        assertEquals(Optional.of(selectedMilestone), PickerMilestone.getSelectedMilestone(listWithSelected));
    }

    @Test
    public void getSelectableMilestones_haveExistingMilestone_doesNotReturnExistingMilestone() {
        List<PickerMilestone> selectableMilestones = PickerMilestone.getSelectableMilestones(listWithExisting);
        assertEquals(listWithExisting.size() - 1, selectableMilestones.size());
        assertEquals(openMilestone1, selectableMilestones.get(0));
    }
}
