package edu.uw.tcss.app.view;

import edu.uw.tcss.app.model.PropChangeEnabledShapeCreatorControls;
import edu.uw.tcss.app.model.ShapeCreator;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.Serial;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * Graphical user interface for the sketch pad application. Holds and manages
 * all the subcomponents.
 *
 * @author Charles Bryan
 * @author Steven Golob
 * @version Winter 2025
 */
public class SketcherGui extends JPanel {

    /**
     * A generated serial version UID for object Serialization.
     * <a href="http://docs.oracle.com/javase/7/docs/api/java/io/Serializable.html">...</a>
     */
    @Serial
    private static final long serialVersionUID = 8452917670991316606L;

    // constants to capture screen dimensions
    /** A ToolKit. */
    private static final Toolkit KIT = Toolkit.getDefaultToolkit();

    /** The Dimension of the screen. */
    private static final Dimension SCREEN_SIZE = KIT.getScreenSize();

    private final PropChangeEnabledShapeCreatorControls myDrawingTool;

    /** The painting surface for the PowerPaint application. */
    private final SketcherCanvas myPaintPanel;

    private final SketcherToolBar myToolBar;

    /**
     * Creates and sets-up a sketch pad GUI to be displayed.
     *
     * @param theDrawingTool the back-end logic for the sketch pad application
     */
    public SketcherGui(final PropChangeEnabledShapeCreatorControls theDrawingTool) {
        super(new BorderLayout());
        myDrawingTool = theDrawingTool;
        myPaintPanel = new SketcherCanvas(myDrawingTool);
        myToolBar = new SketcherToolBar(myDrawingTool);
        layoutComponents();
        addListeners();
    }

    private void layoutComponents() {
        add(myPaintPanel, BorderLayout.CENTER);
        add(myToolBar, BorderLayout.NORTH);
    }

    private void addListeners() {
        myDrawingTool.addPropertyChangeListener(myPaintPanel);
        myDrawingTool.addPropertyChangeListener(myToolBar);
    }

    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event-dispatching thread.
     * <p>
     * NOTE: This is the place where all the parts and pieces of this project are in
     *      the same place. This is where we should instantiate our Model and add it to the
     *      controller and view.
     */
    public static void createAndShowGui() {
        final JFrame frame = new JFrame("Sketch to your heart's delight");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //The Model
        final ShapeCreator drawingTool = new ShapeCreator();

        final SketcherGui sketchPad = new SketcherGui(drawingTool);

        frame.setContentPane(sketchPad);

        // position the frame in the center of the screen
        frame.pack();
        frame.setLocation(SCREEN_SIZE.width / 2 - frame.getWidth() / 2,
                SCREEN_SIZE.height / 2 - frame.getHeight() / 2);

        frame.setVisible(true);
    }


}