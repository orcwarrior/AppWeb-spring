/**
 * Created by Dariusz on 2014-05-20.
 */

var GLOBAL_FADETIME = 100;
jQuery(document).ready( function() {
    jQuery('.uiContent').fadeIn(GLOBAL_FADETIME);


    // TODO: Porównanie linka z obecnym, jezeli rózni się tylko POSTem, anuluj przejscie
    /*
     * Disabled untlil TO DO is done
     *
    var aClickDisabled = false;
    jQuery( "a" ).click(function(e) {
        if(aClickDisabled) {
            aClickDisabled = false;
            // don't prevent click event, it was called by script
        }
        else {
            e.preventDefault();
            jQuery('.uiContent').fadeOut(GLOBAL_FADETIME).promise().done( function(){
                aClickDisabled = true;
                e.currentTarget.click();
            });
        }
    }); // a.click
     */

}); // document.ready

// Newline to <BR>
function newline2br (str, is_xhtml) {
    var breakTag = (is_xhtml || typeof is_xhtml === 'undefined') ? '<br />' : '<br>';
    return (str + '').replace(/([^>\r\n]?)(\r\n|\n\r|\r|\n)/g, '$1'+ breakTag +'$2');
}