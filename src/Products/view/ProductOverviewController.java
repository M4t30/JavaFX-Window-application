package Products.view;

/**
 * Created by M4teo on 20.03.2017.
 */
import Products.*;
import Products.model.Product;
import Products.model.ProductType;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert;


public class ProductOverviewController {
    @FXML
    private TableView<Product> productTable;
    @FXML
    private TableColumn<Product, String> productNameColumn;
    @FXML
    private TableColumn<Product, ProductType> productTypeColumn;

    @FXML
    private Label productNameLabel;
    @FXML
    private Label productTypeLabel;
    @FXML
    private Label productAmountLabel;
    @FXML
    private Label productRefunabilityLabel;

    // Reference to the main application.
    private MainApp mainApp;

    /**
     * The constructor.
     * The constructor is called before the initialize() method.
     */
    public ProductOverviewController() {
    }

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
        // Initialize the person table with the two columns.
        productNameColumn.setCellValueFactory(cellData -> cellData.getValue().productNameProperty());
        productTypeColumn.setCellValueFactory(cellData -> cellData.getValue().productTypeObjectProperty());

        showProductDetails(null);

        productTable.getSelectionModel().selectedItemProperty().addListener(
                ((observable, oldValue, newValue) -> showProductDetails(newValue)));
    }

    @FXML
    private void handleNewProduct(){
        Product tempProduct = new Product();
        boolean okClicked = mainApp.showProductEditDialog(tempProduct);
        if(okClicked){
            mainApp.getProductData().add(tempProduct);
        }
    }

    @FXML
    private void handleEditProduct(){
        Product selectedProduct = productTable.getSelectionModel().getSelectedItem();
        if(selectedProduct!=null) {
            boolean okClicked = mainApp.showProductEditDialog(selectedProduct);
            if(okClicked)
                showProductDetails(selectedProduct);
        }
        else{
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("No selection");
            alert.setHeaderText("No product selected");
            alert.setContentText("Please select a product and try again");
            alert.showAndWait();
        }
    }


    @FXML
    private void handleDeleteProduct(){
        int selectedIndex = productTable.getSelectionModel().getSelectedIndex();
        if(selectedIndex>=0)
            productTable.getItems().remove(selectedIndex);
        else
        {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("Not selected");
            alert.setHeaderText("No product selected");
            alert.setContentText("Please select a product and try again");
            alert.showAndWait();
        }
    }



    /**
     * Is called by the main application to give a reference back to itself.
     *
     * @param mainApp
     */
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;

        // Add observable list data to the table
        productTable.setItems(mainApp.getProductData());
    }

    private void showProductDetails(Product aProduct) {
        if (aProduct != null) {
            // Fill the labels with info from the person object.
            productNameLabel.setText(aProduct.getName());
            productAmountLabel.setText(Integer.toString(aProduct.getAmount()));
            productTypeLabel.setText(aProduct.getType().toString());
            if(aProduct.isRefundability())
                productRefunabilityLabel.setText("TRUE");
            else
                productRefunabilityLabel.setText("FALSE");


        } else {
            // Product is null, remove all the text.
            productNameLabel.setText("");
            productAmountLabel.setText("");
            productTypeLabel.setText("");
            productRefunabilityLabel.setText("");
        }
    }
}