
(function ($) {
    "use strict";
    var mainApp = {

        main_fun: function () {
           
            /*====================================
              LOAD APPROPRIATE MENU BAR
           ======================================*/
            $(window).bind("load resize", function () {
                if ($(this).width() < 768) {
                    $('div.sidebar-collapse').addClass('collapse')
                } else {
                    $('div.sidebar-collapse').removeClass('collapse')
                }
            });

          
     
        },

        initialization: function () {
            mainApp.main_fun();

        }

    }
    // Initializing ///

    $(document).ready(function () {
        mainApp.main_fun();
        
        $(".change-order-status").change(function(){
        	        	
        	var status = $(this).children("option:selected").val();
        	var id = $(this).parent().attr("class");
        	
        	$.ajax({
        		url : "change-order-status",
        		type : "POST",
        		data : {
        			status : status,
        			id : id
        		},
        		success : function(result) {
        			$("#table-order").html(result);
        			alert("Đã cập nhật vào CSDL thành công!");
        		},
        		error : function(jqXhr, textStatus, errorThrown) {
        			alert("Co loi xay ra");
        		}
        	});
        	
        });
        
    });

}(jQuery));
