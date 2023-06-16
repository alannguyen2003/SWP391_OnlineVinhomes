$(document).ready(function() {
  $('[data-toggle="collapse1"]').click(function() {
    var target = $(this).data('target');
    var isExpanded = $(target).hasClass('show');

    // Toggle the collapse
    $(target).collapse('toggle');

    // Update the chevron icon
    $(this).find('.fa-chevron-down').toggleClass('collapsed', isExpanded);
  });
});