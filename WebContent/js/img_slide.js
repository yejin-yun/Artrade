$(document).ready(function() { 
    var myIndex = 0;
    carousel();
    
    function carousel() {
        var i;
        var x = document.getElementsByClassName("img_slides");
        for (i = 0; i < x.length; i++) {
            x[i].style.display = "none";  
        }
        myIndex++;
        if (myIndex > x.length) {myIndex = 1}    
        x[myIndex-1].style.display = "block";  
        setTimeout(carousel, 3000); // Change image every 2 seconds
    } 
});
$(document).ready(function(){
  var myIndex = 0;
    carousel();
    
    function carousel() {
        var i;
        var x = document.getElementsByClassName("img_slides2");
        for (i = 0; i < x.length; i++) {
            x[i].style.display = "none";  
        }
        myIndex++;
        if (myIndex > x.length) {myIndex = 1}    
        x[myIndex-1].style.display = "block";  
        setTimeout(carousel, 3000); // Change image every 2 seconds
    } 

    $('.img_slides2').mouseenter(function(){
        $('.info').css('visibility', 'visible');
    });

    $('.img_slides2').mouseleave(function(){
        $('.info').css('visibility', 'hidden');
    });

});


/*
$(document).ready(function() { 
            var myIndex = 0;
            carousel();
            
            function carousel() {
                var i;
                var x = document.getElementsByClassName("img_slides");
                for (i = 0; i < x.length; i++) {
                    x[i].style.display = "none";  
                }
                myIndex++;
                if (myIndex > x.length) {myIndex = 1}    
                x[myIndex-1].style.display = "block";  
                setTimeout(carousel, 3000); // Change image every 2 seconds
            } 
        });
        $(document).ready(function(){
          var myIndex = 0;
            carousel();
            
            function carousel() {
                var i;
                var x = document.getElementsByClassName("img_slides2");
                for (i = 0; i < x.length; i++) {
                    x[i].style.display = "none";  
                }
                myIndex++;
                if (myIndex > x.length) {myIndex = 1}    
                x[myIndex-1].style.display = "block";  
                setTimeout(carousel, 3000); // Change image every 2 seconds
            } 

            $('.img_slides2').mouseenter(function(){
                $('.info').css('visibility', 'visible');
            });

            $('.img_slides2').mouseleave(function(){
                $('.info').css('visibility', 'hidden');
            });

        });

*/