/**
 * Created by orcwarrior on 2014-07-17.
 */

var GREET_WIDGET_TOGGLEFX_DURATION = 200;
var GREET_WIDGET_KEEPOPEN_DURATION = 2000;
var greetWidgetCanBeToggled = true;
var greetWidgetIsClosed = true;
var greetWidgetInterruptToggleAction = false;

jQuery('.widget-greet-min').ready(function () {
    // TODO: check when last time widget was shown
    // Create some table that will store user session key and userID (if is loged)
    // plus time when he last was seen on page

    greetWidgetToggle(200);
    // query close after some time:
    greetWidgetToggle(GREET_WIDGET_KEEPOPEN_DURATION);
    // onMouseIn: Hold div from slidingUp
    jQuery('#widgetGreetPanel').bind("mouseover click", function () {
        console.log("widget-greet mouseover/click event.");
        if (greetWidgetIsClosed)
            greetWidgetOpen(0);
        else {
            console.log("..widget-greet interuppt activated.");
            greetWidgetInterruptToggleAction = true;
        }
    });
    //onMouseOut: Stop above effect:
    jQuery('#widgetGreetPanel').bind("mouseout", function () {
        if (!greetWidgetIsClosed) {
            console.log("widget-greet mouseout.");
            greetWidgetClose(GREET_WIDGET_KEEPOPEN_DURATION);
        }
    });
});
function greetWidgetOpen(delay){
    greetWidgetInterruptToggleAction = false; // clear 'old' interrupts
    setTimeout(function () {
        if (greetWidgetInterruptToggleAction) {
            console.log("widget-greet interrupted.");
            return;
        }
        if (greetWidgetCanBeToggled)
            _greetWidgetOpen();

    }, delay);

}
function greetWidgetClose(delay){
    greetWidgetInterruptToggleAction = false; // clear 'old' interrupts
    setTimeout(function () {
        if (greetWidgetInterruptToggleAction) {
            console.log("widget-greet interrupted.");
            return;
        }
        if (greetWidgetCanBeToggled)
            _greetWidgetClose();

    }, delay);
}
function greetWidgetToggle(closeDelay,closeEvent) {
    greetWidgetInterruptToggleAction = false; // clear 'old' interrupts
    setTimeout(function () {

        if (greetWidgetInterruptToggleAction) {
            console.log("widget-greet interrupted.");
            return;
        }

        if (greetWidgetCanBeToggled) {
            if (!greetWidgetIsClosed) _greetWidgetClose();
            else                      _greetWidgetOpen();
        }
    }, closeDelay);
}
function _greetWidgetClose() {
    if (greetWidgetCanBeToggled && !greetWidgetIsClosed) {
        greetWidgetCanBeToggled = false;

        // Buttons appereance come first (reverse order than in close):
        jQuery('.widget-greet-body .widget-greet-spacer').toggleClass("widget-greet-spacer-min", GREET_WIDGET_TOGGLEFX_DURATION);
        jQuery('.widget-greet-body .widget-greet-buttonsWrapper').toggleClass("widget-greet-buttonsWrapper-min", GREET_WIDGET_TOGGLEFX_DURATION);

        setTimeout(function () {
            jQuery('.widget-greet-body').toggleClass("widget-greet-body widget-greet-min", GREET_WIDGET_TOGGLEFX_DURATION);
            jQuery('.widget-greet-body h2').toggleClass("widget-greet-captions-min", GREET_WIDGET_TOGGLEFX_DURATION);
            jQuery('.widget-greet-body .widget-greet-icon').toggleClass("widget-greet-icon-min", GREET_WIDGET_TOGGLEFX_DURATION);
            setTimeout(function () {
                    greetWidgetIsClosed = !greetWidgetIsClosed;
                    console.log("widget-greet new state: " + (greetWidgetIsClosed ? "closed" : "opened"));
                    greetWidgetCanBeToggled = true;
                },
                GREET_WIDGET_TOGGLEFX_DURATION);

        }, GREET_WIDGET_TOGGLEFX_DURATION / 2);
    }
}
function _greetWidgetOpen() {
    if (greetWidgetCanBeToggled && greetWidgetIsClosed) {
        greetWidgetCanBeToggled = false;
        jQuery('.widget-greet-min').toggleClass("widget-greet-body widget-greet-min", GREET_WIDGET_TOGGLEFX_DURATION);
        jQuery('.widget-greet-min h2').toggleClass("widget-greet-captions-min", GREET_WIDGET_TOGGLEFX_DURATION);
        jQuery('.widget-greet-min .widget-greet-icon').toggleClass("widget-greet-icon-min", GREET_WIDGET_TOGGLEFX_DURATION);

        // Buttons appereance is delayed:
        setTimeout(function () {
            jQuery('.widget-greet-min .widget-greet-spacer').toggleClass("widget-greet-spacer-min", GREET_WIDGET_TOGGLEFX_DURATION);
            jQuery('.widget-greet-min .widget-greet-buttonsWrapper').toggleClass("widget-greet-buttonsWrapper-min", GREET_WIDGET_TOGGLEFX_DURATION);
            setTimeout(function () {
                    greetWidgetIsClosed = !greetWidgetIsClosed;
                    console.log("widget-greet new state: " + (greetWidgetIsClosed ? "closed" : "opened"));
                    greetWidgetCanBeToggled = true;
                },
                GREET_WIDGET_TOGGLEFX_DURATION);

        }, GREET_WIDGET_TOGGLEFX_DURATION / 2);

    }
}