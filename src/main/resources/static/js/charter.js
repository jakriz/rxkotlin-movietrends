$(document).ready(function() {
    var colors = ["#4D4D4D", "#5DA5DA", "#FAA43A", "#60BD68", "#F17CB0", "#B2912F", "#B276B2", "#DECF3F", "#F15854"];

    $.getJSON("/trends/russell_crowe", function(chartData) {
        console.log(chartData);

        var chartContext = $("#the-chart");
        var chart = new Chart(chartContext, {
            type: 'line',
            data: {
                datasets: _.flatten(_.map(chartData, function(siteData, index) {
                    return [
                        {
                            label: siteData.name,
                            fill: false,
                            lineTension: 0.1,
                            borderColor: colors[2*index],
                            data: _.map(siteData.movies, function(movie) {
                                return {
                                    x: movie.year,
                                    y: movie.rating
                                };
                            })

                        }, {
                            label: siteData.name + " Trendline",
                            fill: false,
                            lineTension: 0.1,
                            borderColor: colors[2*index+1],
                            data: [
                                {
                                    x: siteData.trend.beginning.first,
                                    y: siteData.trend.beginning.second
                                }, {
                                    x: siteData.trend.end.first,
                                    y: siteData.trend.end.second
                                }
                            ]
                        }
                    ]
                }))
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
