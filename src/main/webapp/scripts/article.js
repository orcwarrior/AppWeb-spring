/**
 * Created by Dariusz on 2014-06-03.
 */
// CONSTANTS
var ART_ID_PREFIX = "#article_";
var ARTICLE_STATE_READ = 0, ARTICLE_STATE_EDIT = 1;

function editArticle(articleID) {
    _setupEditorContent(articleID);
    _changeArticleState(articleID, ARTICLE_STATE_EDIT);
}

function finishArticleEditing(articleID) {
    // DK: Now, content is automatically taken from p:editor value
    var title = jQuery(ART_ID_PREFIX + articleID + " h3").text();
    // (JSF messup things with auto-naming forms ids/names but ends of them are as we defined)
    jQuery(ART_ID_PREFIX + articleID + " > form > input[id$='articleTitle']").val(title);

    _changeArticleState(articleID, ARTICLE_STATE_READ);
}

function adaptNewsContentAndEditorSizes(articleID) {
    var articleContentElem = jQuery(ART_ID_PREFIX + articleID + " .article-content-p");
    var articleEditorElem = jQuery("#editorFrame_" + articleID);
    var articleContentEditorWrapperElem = jQuery(ART_ID_PREFIX + articleID + " .article-contentAndEditor-wrapper");

    var height;
    if( jQuery(ART_ID_PREFIX + articleID + " .article-content-p").is(":visible") )
        height = articleContentElem.height();// + parseInt(articleContentElem.css('top'));
    else
        height = jQuery("#editorFrame_" + articleID).height();
    articleContentEditorWrapperElem.height(height);
}

function setupEditorIframeID(articleID) {
    jQuery(ART_ID_PREFIX + articleID + " iframe").attr('id', 'editorFrame_' + articleID);

}

function updateEditorHeight(articleID) {
    var editorFrame = jQuery("#editorFrame_" + articleID);
    var editorDocHeight = editorFrame.get(0).contentWindow.document.body.offsetHeight + 'px';

    jQuery(ART_ID_PREFIX + articleID + " .ui-editor").css('height', editorDocHeight);
    editorFrame.css({overflow: 'hidden', height: editorDocHeight});
    adaptNewsContentAndEditorSizes(articleID);
}

function initializeArticleEditor() {
    // These global values if changed before p:edior init are aplied to further p:editor initialization
    jQuery.cleditor.defaultOptions.bodyStyle = "";
    jQuery.cleditor.defaultOptions.width = "100%";
    jQuery.cleditor.defaultOptions.docCSSFile = "/app/resources/styles/page/article-editor-iframe.css";
}

// Event: On Document ready:
jQuery(document).ready(function () {
    // Unbind that shitty fx on toolbox buttons:
    jQuery('.ui-editor-button').unbind('mouseover').unbind('mouseout');
});

// 'private' functions
function _setupEditorContent(articleID) {
    var content = jQuery(ART_ID_PREFIX + articleID + " .article-content-p").html();
    var editorWidget = PrimeFaces.widgets['articleEditorWidget' + articleID];

    editorWidget.editor.focus();
    setTimeout(function () {
        editorWidget.clear();
        editorWidget.editor.execCommand('inserthtml', content, false);
    }, 0);
    return false;
}

function _changeArticleState(articleID, ARTICLE_STATE) {
    if (ARTICLE_STATE == ARTICLE_STATE_EDIT) {

        jQuery(ART_ID_PREFIX + articleID + " h3").attr("contenteditable", "true");
        jQuery(ART_ID_PREFIX + articleID + " .article-content-p").hide();
        jQuery(ART_ID_PREFIX + articleID + " .ui-editor").css("visibility", "visible");

        jQuery(ART_ID_PREFIX + articleID + " .article-edit-button").hide();
        jQuery(ART_ID_PREFIX + articleID + " .article-editAprove-button").show();
    }
    else if (ARTICLE_STATE == ARTICLE_STATE_READ) {

        jQuery(ART_ID_PREFIX + articleID + " h3").attr("contenteditable", "false");
        jQuery(ART_ID_PREFIX + articleID + " .article-content-p").show();
        jQuery(ART_ID_PREFIX + articleID + " .ui-editor").css("visibility", "collapsed");

        jQuery(ART_ID_PREFIX + articleID + " .article-edit-button").show();
        jQuery(ART_ID_PREFIX + articleID + " .article-editAprove-button").hide();
    }
}