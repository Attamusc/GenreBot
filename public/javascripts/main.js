$(document).ready(function() {
    $("#music-select").on("click", function(e) {
        e.preventDefault();
        $("#music-file").click();
    });

    $("#go").on("click", function(e) {
        $("form").submit();
    });

    // Override the default <body> ondrop event so that we don't get weird behavior if the user misses the turntable.
    $("body").on("drop", function(e) {
        e.preventDefault();
    });

    $('#analysis h1').on('click', function() {
      var display = $('#analysis p').css('display');
      if (display === 'none') {
        $('#analysis p').fadeIn("normal");
      }
      else {
        $('#analysis p').fadeOut("normal");
      }
    });

    document.getElementById("turntable").addEventListener("drop", function(e) {
        e.stopPropagation();
        e.preventDefault();
        // Add the drag-and-dropped file to the FileList of the input[file] element.
        document.getElementById('music-file').files[0] = e.dataTransfer.files[0];
    }, false);
});