
$(document).ready(function() {

    /* Notes App */
    $("#notes-form").submit(function(event) {
        event.preventDefault();

        /* Declare the note-taking variables */
        var $noteIcon = $('<p>&#8226;</p>');
        var $noteText = $noteIcon.text() + " " + $("#input-notes").val();
        var $noteLine = $('<div class="note-line"></div>');
        var $note = $('<p class="note-text"></p>');
        var $deleteBtn = $('<a href="#" class="btn btn-danger btn-sm note-delete-btn">Delete</a>');

        /* Append the note-taking variables to the notes form */
        $note.text($noteText).append($deleteBtn);
        $noteLine.append($note);
        $("#notes").append($noteLine);

        /* Hide the delete button */
        $deleteBtn.hide();

        /* When a note line is clicked, show the delete button */
        $('.note-line').click(function () {
            $(this).children().children().show("slow");

            /* When the delete button is clicked */
            $('.note-delete-btn').click(function(event) {
                event.preventDefault();
                $(this).parent().parent().remove();

            }); //#note-delete-btn click();
        }); //#note-line click();
    }); //notes-form submit();

    /* Theme Controller */
    $(".theme-links").click(function () {
        var $href, $theme;
        /* Get text from theme selection dropdown in the navbar */
        $theme = $(this).text().toLowerCase();
        /* Create href to default/selected theme */
        if ($(this).text() == "Default") {
            $href = "http://bootswatch.com/superhero/bootstrap.min.css";
        } else {
            $href = "http://bootswatch.com/" + $theme + "/bootstrap.min.css";
        }
        /* Change href to the BootstrapCDN CSS link */
        $("#bootstrapcdn-link").prop("href", $href);
    }); //.theme-links click();
}); //document ready();
