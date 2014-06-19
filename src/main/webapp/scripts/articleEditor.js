/**
 * Created by Dariusz on 2014-06-13.
 */
// KEEP ORDER AS IN HTML DOCUMENT
var arrFormatCmds = ['bold','italic','underline','link','image','insertunorderedlist'];
var arrCmdHints = {'bold':'Pogrubienie tekstu',
                  'italic':'Kursywa tekstu',
                  'underline':'Podkreślenie',
                  'link':'Wstaw link',
                  'image':'Wstaw obrazek',
                  'insertorderedlist':'Wstaw liste numerowaną',
                  'insertunorderedlist':'Wstaw listę wypunktowaną',
                   undefined:'',
                   null:''}

function formatDoc(cmd, value) {
    //TODO: Explictly proccess only commands from FormatCmds array
        document.execCommand(cmd, false, value);
}
    jQuery(document).ready( function() {
        // Initialize buttons datas (it's not allowed to enter them just in html :()
        jQuery('.newsModifyToolbox').each(function(){
            var i=0;
           jQuery(this).children('.newsToolboxButton').data('formatcmd', arrFormatCmds[i++] )
                       .next('.newsToolboxButton').data('formatcmd', arrFormatCmds[i++] )
                       .next('.newsToolboxButton').data('formatcmd', arrFormatCmds[i++] )
                       .next('.newsToolboxButton').data('formatcmd', arrFormatCmds[i++] )
                       .next('.newsToolboxButton').data('formatcmd', arrFormatCmds[i++] )
                       .next('.newsToolboxButton').data('formatcmd', arrFormatCmds[i++] );
        });
        // Buttons onclick:
        var element = jQuery('.newsModifyToolbox > .newsToolboxButton');
        element.click( function() {
            var formatCmd = jQuery(this).data('formatcmd');
            document.execCommand(formatCmd,false,null);
        });
        // Buttons onfocus:
        jQuery('.newsModifyToolbox > .newsToolboxButton').mouseover(function() {
            var formatCmd = jQuery(this).data('formatcmd');
            jQuery(this).parent().next(".newsModifyToolboxDescription").text(arrCmdHints[formatCmd]);

        });
        jQuery('.newsModifyToolbox > .newsToolboxButton').mouseout(function() {
            jQuery(this).parent().next(".newsModifyToolboxDescription").text(arrCmdHints[null]);
        });
    });