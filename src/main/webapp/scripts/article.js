/**
 * Created by Dariusz on 2014-06-03.
 */
// CONSTANTS
function editArticle(articleID)
{
    var title = jQuery("#news_"+articleID+" > h3").text();
    var content = jQuery("#news_"+articleID+" > p").text();

    jQuery("#news_"+articleID+" > h3").attr("contenteditable","true");
    jQuery("#news_"+articleID+" > .newsContentAndEditorWrapper > .newsContentEditor").css("visibility","visible");

    // swap buttons (hide edit, show accept):
    jQuery("#news_"+articleID+" > .newsEditButton").hide();
    jQuery("#news_"+articleID+" > form > .newsModifyModFinishButton").show();

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
    // jQuery('.newsModifyContentParagraph').focusout(function() {
    //     jQuery(this).parent().children(".newsModifyToolbox").fadeOut('fast');
    // });
    // jQuery('.newsModifyContentParagraph').focus(function() {
    //     jQuery(this).parent().children(".newsModifyToolbox").fadeIn('fast');
    // });
    // jQuery(".newsModifyToolbox").hide();

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

function adaptNewsContentAndEditorSizes(articleID)
{
    var newsContent = jQuery("#news_" +articleID+ " > .newsContentAndEditorWrapper > .newsContent");
    var newsContentEditor = jQuery("#news_" +articleID+ " > .newsContentAndEditorWrapper > .newsContentEditor");
    var newsContentAndEditorWrapper =  jQuery("#news_" +articleID+ " > .newsContentAndEditorWrapper");
    // width of content and editor from wrapper width
    var width = newsContentAndEditorWrapper.width();
    newsContent.width(width);
    newsContentEditor.width(width);
    var maxHeight;
    maxHeight = newsContent.height() + parseInt(newsContent.css('top'));
    maxHeight = Math.max(maxHeight, newsContentEditor.height() + parseInt(newsContentEditor.css('top')));

    newsContentAndEditorWrapper.height(maxHeight);
}

// Event: On Document ready:
jQuery(document).ready( function() {
    // Unbind that shitty fx:
    jQuery('.ui-editor-button').unbind('mouseover');
    jQuery('.ui-editor-button').unbind('mouseout');
});