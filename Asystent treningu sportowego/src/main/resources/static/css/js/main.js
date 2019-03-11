$(document).ready(function () {

    $('.eBtn').on('click', function (event) {
        event.preventDefault();
        var href = $(this).attr('href');
        //for update user
        $.get(href, function (currnetgoals, status) {
             $('.myFormUpdate #id').val(currnetgoals.id);
            $('.myFormUpdate #nazwa').val(currnetgoals.nazwa);
            $('.myFormUpdate #typeactivity').val(currnetgoals.typeactivity);
            $('.myFormUpdate #typegoals').val(currnetgoals.typegoals);
            $('.myFormUpdate #goal').val(currnetgoals.goal);
            $('.myFormUpdate #stopgoals').val(currnetgoals.stopgoals);
            $('.myFormUpdate #startgoals').val(currnetgoals.startgoals);
            $('.myFormUpdate #updateModal').modal();

        });
    });
});


$(document).ready(function () {
    $('.btnnn').on('click', function (event) {
        event.preventDefault();
        var href = $(this).attr('href');
        $.get(href, function (exercise) {
            $('.myFormDetail #nazwa').val(exercise.nazwa);
            $('.myFormDetail #opis').val(exercise.opis);
            $('.myFormDetail #showdetail').modal();
        });
    });
});



$(document).ready(function () {

    $('.table .delBtn').on('click', function (event) {
        event.preventDefault();
        var href = $(this).attr('href');
         $('#removeModalCenter #delRef').attr('href', href);
         $('#removeModalCenter').modal()
    });
});


$(document).ready(function () {

    $('.table .nBtn').on('click', function (event) {
        event.preventDefault();
        var href = $(this).attr('href');
        $.get(href, function (currnetgoals, status) {
             $('.myFormAdd #addModal').modal();

        });
    });
});


$(document).ready(function () {

    $('.noteBtn').on('click', function (event) {
        event.preventDefault();
        var href = $(this).attr('href');
        $.get(href, function (detailsTraining) {
            // $('.myFormNote #id').val(trainingDay.id);
            $('.myFormNote #notatka').val(detailsTraining.notatka);
            $('.myFormNote #noteModal').modal();

        });
    });
});