//<![CDATA[
$(document).ready(async function () {
    'use strict'

//    const url = document.URL;
//    const params = url.extract();
//    const sc2Ticket = params.auth;
//    localStorage.setItem(DXCUtils.SC2_TICKET_KEY, sc2Ticket);
//    await DXCUtils.userInfoRender();
    $.each($('form'), function (_, valueOfElement) {
        $(valueOfElement).on('submit', function (event) {
            event.preventDefault();
            return false;
        });
    });
    $('.ui.dimmer').dimmer({
        closable: false
    });
    $('body').on('click', '#menucaller', function () {
        $('.ui.sidebar')
            .sidebar('setting', 'transition', 'push')
            .sidebar('toggle');
    });
    // required for set active menu
//    DXCUtils.setActiveMenu($('#currentScreenId').val());
    // end required for set active menu
    // menu and button authorization
//    await DXCUtils.authorizationRender($('#currentScreenId').val());
    // end menu and button authorization
//    DXCUtils.addTicketToUrl();

    // timer
//    const displayDBServerTime = async function () {
//        const dbTime = await DXCUtils.callAPI("/common/dbservertime", "GET");
//        const dateTime = new Date(dbTime);
//        $('.time').text(moment(dateTime).format('DD MMM YYYY HH:mm:ss'));
//        setInterval(function () {
//            dateTime.setTime((dateTime.getTime() + 1000));
//            $('.time').text(moment(dateTime).format('DD MMM YYYY HH:mm:ss'));
//        }, 1000);
//    };
    // call displayDBServerTime immediately
//    displayDBServerTime();
    // repeat call every 5 min to for update
//    setInterval(displayDBServerTime, 300000);
});
//]]>