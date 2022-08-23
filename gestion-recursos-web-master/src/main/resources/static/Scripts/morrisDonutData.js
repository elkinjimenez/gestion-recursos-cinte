function morrisDonutData(element, data, colors) {
    $("."+element).show();
    $("#"+element).empty();
    chart = Morris.Donut({
        element: element,
        data: data,
        colors: colors
    });
}