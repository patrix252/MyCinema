/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
var firstSeatLabel = 1;

            $(document).ready(function () {
                $("#acquista").hide();
            });
		
            function mappa(posti) {
                var postiPerSpettacolo = new Array();
                for(var i=0; i<posti.length; i++){
                    if(posti[i][0]==$("#ora").val()){
                        for(var j=0; j<posti[i].length; j++){
                            postiPerSpettacolo.push(posti[i][j]);
                        }
                    }
                }
                    $("#acquista").show();
                    var $cart = $('#selected-seats'),
                    $counter_intero = $('#counter_intero'),
                    $counter_ridotto = $('#counter_ridotto'),
                    $total = $('#total'),
                    sc = $('#seat-map').seatCharts({
                        map: [
                                'ffffffffff',
                                'ffffffffff',
                                'ffffffffff',
                                'ffeffffeff',
                                '__________',
                                'ffffffffff',
                                'ffffffffff',
                                'ffffffffff',
                                'ffffffffff',
                        ],
                        seats: {
                            f: {
                                    price   : 100,
                                    classes : 'first-class', //your custom CSS class
                                    category: 'First Class'
                            },
                            e: {
                                    price   : 40,
                                    classes : 'first-class economy-class', //your custom CSS class
                                    category: 'Economy Class'
                            }					

                        },
                        naming : {
                            top : false,
                            getLabel : function (character, row, column) {
                                    return firstSeatLabel++;
                            },
                        },
                        legend : {
                            node : $('#legend'),
                            items : [
                                        [ 'f', 'available',   'First Class' ],
                                        [ 'e', 'available',   'Economy Class'],
                                        [ 'f', 'unavailable', 'Already Booked']
                            ]					
                        },
                        click: function () {
                            if (this.status() == 'available') {
                                    
                                
                                    //let's create a new <li> which we'll add to the cart items
                                    $('<li>'+this.data().category+' Seat # '+this.settings.label+': <b>$'+this.data().price+'</b> <a href="#" class="cancel-cart-item">[cancel]</a></li>')
                                            .attr('id', 'cart-item-'+this.settings.id)
                                            .data('seatId', this.settings.id)
                                            .appendTo($cart);

                                    /*
                                     * Lets update the counter and total
                                     *
                                     * .find function will not find the current seat, because it will change its stauts only after return
                                     * 'selected'. This is why we have to add 1 to the length and the current seat price to the total.

     *             */
                                    $counter_intero.text(sc.find('selected').length+1);
                                    $total.text(recalculateTotal(sc)+this.data().price);

                                    return 'selected';
                            } else if (this.status() == 'selected') {
                                    //update the counter
                                    $counter_intero.text(sc.find('selected').length-1);
                                    $counter_ridotto.text(sc.find('selected_ridotto').length+1);
                                    //and total
                                    $total.text(recalculateTotal(sc)-this.data().price);
                                    $total.text(recalculateTotal(sc)+this.data().price);


                                    //seat has been vacated
                                    return 'selected_ridotto';
                            } else if (this.status() == 'selected_ridotto') {
                                    //update the counter
                                    $counter_ridotto.text(sc.find('selected_ridotto').length-1);
                                    //and total
                                    $total.text(recalculateTotal(sc)-this.data().price);

                                    //seat has been vacated
                                    return 'available';
                            } else if (this.status() == 'unavailable') {
                                    //seat has been already booked
                                    return 'unavailable';
                            } else {
                                    return this.style();
                            }
                        }
                    });

                    //this will handle "[cancel]" link clicks
                    $('#selected-seats').on('click', '.cancel-cart-item', function () {
                            //let's just trigger Click event on the appropriate seat, so we don't have to repeat the logic here
                            sc.get($(this).parents('li:first').data('seatId')).click();
                    });
                    
                    sc.find('unavailable').each(function () {
                        this.status('available');
                    });
                    sc.get(postiPerSpettacolo).status('unavailable');

            }

            function recalculateTotal(sc) {
                var total = 0;

                //basically find every selected seat and sum its price
                sc.find('selected').each(function () {
                    total += this.data().price;
                });
                sc.find('selected_ridotto').each(function () {
                    total += this.data().price;
                });

                return total;
            }

            


