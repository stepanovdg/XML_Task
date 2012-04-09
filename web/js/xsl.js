/**
 * Created by IntelliJ IDEA.
 * User: Dzmitry_Stsiapanau
 * Date: 3/29/12
 * Time: 8:22 AM
 */
$(document).ready(function(e) {

    /* $("#addBody").onload(function(e) {
     $("#catNameInput").hide();
     $("#subCatNameInput").hide();
     $("#unitInput").hide();
     var notChecked = "d форму онлоад";
     alert(notChecked);
     });
     $("#catNameSelect").change(function(e) {
     var select = $("#catNameSelect").val();
     if (select == "") {
     $("#catNameInput").show();
     } else {
     $("#catNameInput").hide();
     }
     });  */
    $("#unitStockBox").change(function(e) {
        var checkboxes = $("#unitStockBox");
        var select = checkboxes.is(":checked");
        if (select) {
            $("#unitPrice").hide();
        } else {
            $("#unitPrice").show();
        }
    });

    $("#validateMessage").ready(function(e) {
        var mess = $("#validateMessage").val();

        if (mess != "" && mess != null) {
            alert(mess);
        }

    });

    $("#cancelButton").click(function(e) {
        window.location = "/XMLTask/XSLTServlet?categoryName="
                + $("#categoryName").val() + "&subCategoryName="
                + $("#subCategoryName").val();
    });
});