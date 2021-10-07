'use strict';

$('.dropdown-item').on('click', function() {
    let formId = this.classList[1];
    console.log('formId - ' + formId);
    openbox(formId);
});

function openbox(id) {
    hideOtherForm(id);
    let display = document.getElementById(id).style.display;
    console.log('display - ' + display);
    if (display === 'block') {
        document.getElementById(id).style.display='none';
    } else {
        document.getElementById(id).style.display='block';
    }
}

function hideOtherForm(id) {
    Array.prototype.filter.call($('form'), function(element) {
        return element.id !== id;

    }).forEach(function(element) {
        element.style.display='none';
    });
}


$('select').on('change', function() {
    switch (this.value) {
        case "name":
            $('.action').val('searchBookByName');
            $('input.form-control').attr('name', 'name');
            break;
        case "ISBN" :
            $('.action').val('searchBookByISBN');
            $('input.form-control').attr('name', 'ISBN');
            break;
        case "author":
            $('.action').val('searchBookByAuthor');
            $('input.form-control').attr('name', 'author');
            break;
    }
});
