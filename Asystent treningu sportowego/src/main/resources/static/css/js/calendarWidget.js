$(document).ready(function () {
    $('#calendar').fullCalendar({
        firstDay: 1,
        themeSystem: 'bootstrap4',
        editable: false,
        dragable: false,
        header: {
            left: '',
            center: 'title',
            right: ''
        },
        defaultView: 'listDay',
        timeFormat: 'H:mm',
        allDayText: 'Whole day',
        views: 'listWeek',
        googleCalendarApiKey: 'AIzaSyCC8fWdM8ROyCOpAzrLHDG99T6PFeJRXFI',
        eventSources: [
            {
                googleCalendarId: '704628094900-mokatj1hebtams4c9nl2vrcaq4j9ootl.apps.googleusercontent.com',
                className: 'gcal-event',
                color: '#00E676'

            },
            {
                url : '/api/event/all',
            }
        ]
    });
});