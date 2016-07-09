$(document).ready(function() {
    $.getJSON("/trends/russel_crowe", function(chartData) {
        console.log(chartData);
        console.log(_.map(chartData[0].movies, function(movie) {
               return {
                   x: movie.year,
                   y: movie.rating
               };
           }));

        var chartContext = $("#the-chart");
        var chart = new Chart(chartContext, {
            type: 'line',
            data: {
                datasets: _.map(chartData, function(siteData) {
                    return {
                        label: siteData.name,
                        fill: false,
                        lineTension: 0.1,
                        data: _.map(siteData.movies, function(movie) {
                            return {
                                x: movie.year,
                                y: movie.rating
                            };
                        })
                    }
                })
            },
            options: {
                scales: {
                    xAxes: [{
                        type: 'linear',
                        position: 'bottom'
                    }]
                }
            }
        });
    });
});
