package Products.view;


import javafx.fxml.FXML;
import javafx.collections.FXCollections;

import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.CheckBox;
import Products.model.*;
import javafx.stage.Stage;
import javafx.scene.control.TextField;
import Products.model.Product;
import Products.model.ProductType;

/**
 * Created by M4teo on 21.03.2017.
 */
public class ProductEditDialogController
{
        @FXML
        private TextField productNameField;
        @FXML
        private TextField amountField;
        @FXML
        private ComboBox<ProductType> productTypeComboBox =new ComboBox<>();
        @FXML
        private CheckBox refundabilityCheckBox = new CheckBox();

        private Stage dialogStage;
        private Product product;
        private boolean okClicked = false;

        @FXML
        private void initialize(){
            productTypeComboBox.setItems(FXCollections.observableArrayList(ProductType.values()));
            refundabilityCheckBox.setSelected(true);
        }

        public void setDialogStage(Stage dialogStage){
            this.dialogStage=dialogStage;
        }

        public void setProduct(Product product) {
            this.product = product;
            productNameField.setText(product.getName());
            amountField.setText(Integer.toString(product.getAmount()));
            productTypeComboBox.getSelectionModel().select(product.getType());
            refundabilityCheckBox.setSelected(product.isRefundabilityProperty().getValue());
        }

        public boolean isOkClicked(){
            return okClicked;
        }

        @FXML
        private void handleOk() {
            if (isInputValid()) {
                product.setName(productNameField.getText());

                try {
                    product.setAmount(Integer.parseInt((amountField.getText())));
                } catch (NumberFormatException e) {
                    product.setAmount(0);
                }

                if (product.getAmount() < 0)
                    product.setAmount(0);

                product.setType(productTypeComboBox.getSelectionModel().getSelectedItem());
                product.setRefundability(refundabilityCheckBox.isSelected() && product.getAmount() > 0 ? true : false);

                okClicked = true;
                dialogStage.close();
            }
        }

        @FXML
        private void handleCancel(){
            dialogStage.close();
        }

        private boolean isInputValid(){
            String errorMessage = "";
            if(productNameField.getText()==null || productNameField.getText().length()==0)
                errorMessage+="No valid product name!\n";
            try{
                Integer.parseInt(amountField.getText());
            }catch (NumberFormatException e) {
                errorMessage += "Not an integer number!\n";
            }
            if(productTypeComboBox.getSelectionModel().isEmpty())
                errorMessage+="No type selected!\n";

            if(errorMessage.length()==0) {
                return true;
            }
            else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.initOwner(dialogStage);
                alert.setTitle("Invalid data");
                alert.setHeaderText("Please correct invalid fields");
                alert.setContentText(errorMessage);
                alert.showAndWait();
                return false;
            }
        }
}
