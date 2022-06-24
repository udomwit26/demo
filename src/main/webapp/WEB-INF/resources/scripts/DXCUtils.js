'use strict'
const DXCUtils = (function ($) {
    return {
        COMBOBOX_ALL: 'All',
        COMBOBOX_SELECT: 'Select',
        MODE_ADD: 'ADD',
        MODE_EDIT: 'EDIT',
        SC2_TICKET_KEY: 'sc2Ticket',
        USER_ID_KEY: 'userid',
        toggleGroupMenu: obj => {
            let menuGroup = $(obj).parent();
            menuGroup.children(".menu").toggle();
            // hide all other menu group
            let allOtherMenuGroup = $(obj).parent().siblings();
            if (allOtherMenuGroup != null) {
                for (let ii = 0; ii < allOtherMenuGroup.length; ii++) {
                    let otherMenuGroup = allOtherMenuGroup[ii];
                    if ($(otherMenuGroup).children(".menu").is(':visible')) {
                        $(otherMenuGroup).children(".menu").hide();
                    }
                }
            }
            return false;
        },
        setActiveMenu: screenId => {
            const creenIdObj = $(`#${screenId}`);
            creenIdObj.toggleClass("active");
            creenIdObj.parent().show();
            creenIdObj.parent().parent().siblings().children(".menu").hide();
            return false;
        },
        userInfoRender: async () => {
            $.LoadingOverlay('show');
            const response = await DXCUtils.callAPI("/common/userinfo", "GET");
            localStorage.setItem(DXCUtils.USER_ID_KEY, response.data);
            $('.userinfo').text(response.data);
            $.LoadingOverlay('hide');
            return false;
        },
        authorizationRender: async screenId => {
            $.LoadingOverlay('show');
            try {
                const response = await DXCUtils.callAPI("/common/authorization", "GET");
                // menu group
                response.data.forEach(groups => $(`#${groups.group}`).attr("data-auth", "yes"));
                // menu item
                response.data.forEach(screen => $(`#${screen.screenInfo.screenId}`).attr("data-auth", "yes"));
                // button
                response.data
                    .filter(data => data.screenInfo.screenId == screenId)
                    .map(data => data.screenInfo.buttonInfo)
                    .forEach(buttons => buttons.forEach(button => $(`#${button.buttonId}`).attr("data-auth", "yes")));

                $("[data-auth='no']").empty();
                $("[data-auth='no']").remove();
            } catch (err) {
                $("[data-auth='no']").empty();
                $("[data-auth='no']").remove();
            }
            $.LoadingOverlay('hide');
        },
        addTicketToUrl: () => {
            $.LoadingOverlay('show');
            const sc2Ticket = localStorage.getItem(DXCUtils.SC2_TICKET_KEY);
            let originalhref = '';
            $.each($("[data-tagtype='menuitem']"), (_, element) => {
                originalhref = $(element).attr('href');
                originalhref += `?auth=${sc2Ticket}`;
                $(element).attr('href', originalhref);
            });
            $.LoadingOverlay('hide');
        },
        createSelectOption: (selectObj, comboList, firstItemText) => {
            //selectObj.empty();
            selectObj.find('option').remove().end();
            if (firstItemText != null) {
                selectObj.append($('<option>', {
                    value: firstItemText,
                    text: firstItemText
                }));
            }
            if (comboList != null) {
                for (let ii = 0; ii < comboList.length; ii++) {
                    let item = comboList[ii];
                    selectObj.append($('<option>', {
                        value: item.value,
                        text: item.name
                    }));
                }
            }
        },
        getValueFromFieldOfForm: function (formId, fieldId) {
            return $(`#${formId}`).form('get field', fieldId).val();
        },
        formatDate: function (javaDate, format) {
            if (S(javaDate).isEmpty()) {
                return '';
            } else {
                return moment(new Date(javaDate)).format(format);
            }
        },
        parseDate: function (stringDate, format) {
            if (S(stringDate).isEmpty()) {
                return null;
            } else {
                return moment(stringDate, format).toDate();
            }
        },
        parseDateForDB: function (stringDate, format) {
            if (S(stringDate).isEmpty()) {
                return null;
            } else {
                return moment(stringDate, format).toDate().getTime();
            }
        },
        comfirmModal: function (confirmMessage, params, approveCallback) {
            confirmMessage = confirmMessage.replaceAll('"', '');
            const messageCode = confirmMessage.substring(0, confirmMessage.indexOf(":"));
            $('#confirmingModal').children(".header").children("span").text('');
            $('#confirmingModal').children(".content").empty();
            $('#confirmingModal').children(".header").children("span").removeClass("yellow red green");
            $('#confirmingModal').children(".header").children("span").text(messageCode);
            if (params != null) {
                for (let indexInArray = 0; indexInArray < params.length; indexInArray++) {
                    let valueOfElement = params[indexInArray].replaceAll('"', '');
                    confirmMessage = confirmMessage.replace('{' + indexInArray + '}', valueOfElement);
                }
            }
            const firstConlon = confirmMessage.indexOf(':');
            const messageType = confirmMessage.substring(firstConlon - 3, firstConlon);
            switch (messageType) {
                case 'WRN':
                    $('#confirmingModal').children(".header").children("span").addClass("yellow");
                    break;
                case 'CFM':
                    $('#confirmingModal').children(".header").children("span").addClass("green");
                    break;
            }
            $('#confirmingModal').children(".content").append(confirmMessage);
            return $('#confirmingModal').modal({
                closable: false,
                onDeny: function () {
                    return true;
                },
                onApprove: approveCallback,
                duration: 0
            });
        },
        alertModal: function (alertMessage, params) {
            alertMessage = alertMessage.replaceAll('"', '');
            const messageCode = alertMessage.substring(0, alertMessage.indexOf(":"));
            $('#alertModal').children(".header").children("span").text('');
            $('#alertModal').children(".content").empty();
            $('#alertModal').children(".header").children("span").removeClass("yellow red green");
            $('#alertModal').children(".header").children("span").text(messageCode);
            if (params != null) {
                for (let indexInArray = 0; indexInArray < params.length; indexInArray++) {
                    let valueOfElement = params[indexInArray].replaceAll('"', '');
                    alertMessage = alertMessage.replace(`{${indexInArray}}`, valueOfElement);
                }
            }
            const firstConlon = alertMessage.indexOf(':');
            const messageType = alertMessage.substring(firstConlon - 3, firstConlon);
            switch (messageType) {
                case 'INF':
                    $('#alertModal').children(".header").children("span").addClass("green");
                    break;
                case 'WRN':
                    $('#alertModal').children(".header").children("span").addClass("yellow");
                    break;
                case 'ERR':
                    $('#alertModal').children(".header").children("span").addClass("red");
                    break;
            }
            $('#alertModal').children(".content").append(alertMessage);
            return $('#alertModal').modal({
                closable: false,
                selector: {
                    close: '#modalButtonOK'
                },
                duration: 0
            });
        },
        partNoFormat: function (partNo) {
            if (partNo != null && partNo.length > 0) {
                if (partNo.length > 10) {
                    let a = 1;
                    return partNo.substring(0, 5).concat("-").concat(partNo.substring(5, 10)).concat("-").concat(partNo.substring(10));
                } else if (partNo.length > 5) {
                    return partNo.substring(0, 5).concat("-").concat(partNo.substring(5));
                }
            }
            return partNo;
        },
        focusFirstError: function () {
            const elem = $('.field.error:first').find('input,select,textarea');
            setTimeout(function () {
                $('html, body').animate({
                    scrollTop: $($(elem)[0]).closest('div').offset().top - 100
                }, 'fast', function () {
                    if ($($(elem)[0]).attr("type") != "checkbox") {
                        $(elem).focus();
                    }
                });
            }, 500);
        },
        scrollTop: function () {
            $('html, body').animate({
                scrollTop: 0
            }, 'fast');
        },

        clearMessageRulesErrors: function (selectObj) {
            let $field = selectObj.find('input, textarea, select'),
                $group = selectObj.find('.field');
            $field.each(function () {
                let
                    $field = $(this),
                    $fieldGroup = $field.closest($group),
                    $prompt = $fieldGroup.find('.prompt.label'),
                    isErrored = $fieldGroup.hasClass('error');
                if (isErrored) {
                    $fieldGroup.removeClass('error');
                    $prompt.remove();
                }
            });
        },
        // 0 is equal,-1 less than, 1 more than,2 not same type
        compareNumber: function (numberObj1, numberObj2) {
            if (numberObj1 == null && numberObj2 == null) {
                return 0;
            }
            if (numberObj1 !== numberObj2) {
                return 2;
            }
            if (numberObj1 > numberObj2) {
                return 1;
            } else {
                return -1;
            }
        },
        callAPI: async (uri, httpMethod, postData) => {
            const url = `/mats${uri}`;
            const options = {
                "method": httpMethod,
                headers: {
                    "Content-Type": "application/json; charset=utf-8",
                    "auth": localStorage.getItem(DXCUtils.SC2_TICKET_KEY),
                    "userid": localStorage.getItem(DXCUtils.USER_ID_KEY)
                }
            };
            if (postData != null) {
                options.body = JSON.stringify(postData);
            }
            const response = await fetch(url, options);
            if (response.status != 200) {
                DXCUtils.apiErrorHandler(response.status);
            }
            return response.json();
        },
        apiErrorHandler: httpStatus => {
            $.LoadingOverlay('hide');
            if (httpStatus == 401) {
                const modal = DXCUtils.alertModal("MCOM00006AERR: Authorization failure. Please close this screen and login again.", null);
                modal.modal('show');
            } else if (httpStatus == 403) {
                const modal = DXCUtils.alertModal("MCOM00007AERR: Resource is denied to access.", null);
                modal.modal('show');
            }
        },
        callAPIWithUploadFile: async (uri, httpMethod, postData) => {
            const url = `/mats${uri}`;
            const options = {
                "method": httpMethod,
                headers: {
                    "auth": localStorage.getItem(DXCUtils.SC2_TICKET_KEY),
                    "userid": localStorage.getItem(DXCUtils.USER_ID_KEY)
                }
            };
            if (postData != null) {
                options.body = postData;
            }
            const response = await fetch(url, options);
            if (response.status != 200) {
                DXCUtils.apiErrorHandler(response.status);
            }
            return response.json();
        },
        callAPIWithDownloadFile: async (uri, httpMethod, postData) => {
            const url = `/mats${uri}`;
            const options = {
                "method": httpMethod,
                headers: {
                    "auth": localStorage.getItem(DXCUtils.SC2_TICKET_KEY),
                    "userid": localStorage.getItem(DXCUtils.USER_ID_KEY)
                }
            };
            if (postData != null) {
                options.body = JSON.stringify(postData);
            }
            const response = await fetch(url, options);
            if (response.status != 200) {
                DXCUtils.apiErrorHandler(response.status);
            }
            let contentType = '';
            let fileName = '';
            response.headers.forEach(function (val, key) {
                if (key == 'content-type') {
                    contentType = val;
                }
                if (key == 'content-disposition') {
                    fileName = val.split('=')[1];
                }
            });
            const returnObj = {};
            returnObj.contentType = contentType;
            returnObj.fileName = fileName;
            if (contentType == 'application/json') {
                returnObj.content = await response.json();
            } else {
                returnObj.content = await response.blob();
            }
            return returnObj;
        },
        blobToBase64Url: blob => {
            return new Promise((resolve) => {
                const reader = new FileReader()
                reader.onloadend = () => resolve(reader.result)
                reader.readAsDataURL(blob)
            })
        },
        popupDownloadFile: downloadObj => {
            if (downloadObj.contentType == 'application/json') {
                const modal = DXCUtils.alertModal(downloadObj.content.message, null);
                modal.modal('show');
            } else {
                const link = document.createElement('a');
                link.href = window.URL.createObjectURL(downloadObj.content);
                link.download = downloadObj.fileName;
                document.body.appendChild(link);
                link.dispatchEvent(new MouseEvent('click', {
                    bubbles: true,
                    cancelable: true,
                    view: window
                }));
                link.remove();
                window.URL.revokeObjectURL(link.href);
            }
        },
        isValueInArrayUnique: inputArray => {
            if (_.isEmpty(inputArray)) {
                return true;
            }
            const distinctValue = inputArray.filter((element, index, arrays) => arrays.indexOf(element) == index);
            return distinctValue.length == 1;
        }
    }
}(jQuery));