let is_sending = false; 


window.fbAsyncInit = function() {
    FB.init({appId: "210865283197643", version: "v3.2", xfbml: true });

    FB.Event.subscribe('comment.create', function(comment) {
    	if (is_sending) return false;
        
    	let id_post = $('[name="id"]').val();
      	let id = comment.commentID;
    	let url = "https://graph.facebook.com/v3.2/" + id;

    	$.ajax({
		    url: "saveNotification",
		    data: { 
		    	"id_cmt": id,
		    	"id_post": id_post
	        },
		    type: "GET",
		    beforeSend: function () {
	            is_sending = true;
            },
            complete: function () {
                is_sending = false;
            },
		    success: function(response) {
		    	console.log(response);
		    },
		    error: function(xhr) {

		    }
		});
      
   	});

};
		
(function(d, s, id) {
  var js, fjs = d.getElementsByTagName(s)[0];
  if (d.getElementById(id)) return;
  js = d.createElement(s); js.id = id;
  js.src = 'https://connect.facebook.net/en_US/sdk.js#xfbml=1&version=v3.2&appId=210865283197643&autoLogAppEvents=1';
  fjs.parentNode.insertBefore(js, fjs);
}(document, 'script', 'facebook-jssdk'));


$(document).ready(function(){
	
	$("input.input-number").change(function(){
		var q = $("input.input-number").val();
		$("#product-quantity").val(q);		
	});
	
})