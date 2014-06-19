/**
 * Created by Dariusz on 2014-06-03.
 */
// CONSTANTS
function editArticle(articleID)
{
    var title = jQuery("#news_"+articleID+" > h3").text();
    var content = jQuery("#news_"+articleID+" > p").text();

    updateEditArticleForm(articleID,title,content);
    jQuery("#news_"+articleID).hide();
    jQuery("#newsModify_"+articleID).show();
    createFocusEvents();
}
function createFocusEvents()
{
    jQuery('.newsModifyTitleInput, .newsModifyContentParagraph').focusout(function() {
        jQuery(this).removeClass('newsModifySelectedInput');
    });
    jQuery('.newsModifyTitleInput, .newsModifyContentParagraph').focus(function() {
        jQuery(this).addClass('newsModifySelectedInput');
    });
    // Toolbox fadeing:
    jQuery('.newsModifyContentParagraph').focusout(function() {
        jQuery(this).parent().children(".newsModifyToolbox").fadeOut('fast');
    });
    jQuery('.newsModifyContentParagraph').focus(function() {
        jQuery(this).parent().children(".newsModifyToolbox").fadeIn('fast');
    });
    jQuery(".newsModifyToolbox").hide();

}
function updateEditArticleForm(articleID,articleTitle,articleContent)
{
    jQuery("#newsModify_" + articleID + " > .newsModifyTitleInput").val(articleTitle);
    jQuery("#newsModify_" + articleID + " > .newsModifyContentParagraph").text(articleContent);
}
function finishArticleEditing(articleID)
{
    var _title = jQuery("#newsModify_" +articleID+ " > .newsModifyTitleInput").val();
    var _content = jQuery("#newsModify_" +articleID+ " > .newsModifyContentParagraph").text();
    jQuery("#news_" +articleID+ " > h3").text(_title);
    jQuery("#news_" +articleID+ " > p").html(newline2br(_content));

    jQuery("#news_"+articleID).show();
    jQuery("#newsModify_"+articleID).hide();

    // Update values in hiddenInputs (managedBean grabs value from them)
    // (JSF messup things with auto-naming forms ids/names but ends of them are as we defined)
    jQuery("#newsModify_" +articleID+ " > form > input[id$='articleTitle']").val(_title);
    jQuery("#newsModify_" +articleID+ " > form > input[id$='articleContent'").val(_content);
}


// Event: On Document ready:
jQuery(document).ready( function() {
});