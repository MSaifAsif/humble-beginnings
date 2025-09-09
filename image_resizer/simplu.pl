#!user/bin/perl

use strict;
use warnings;

use Image::Magick;

my $im;

$im=Image::Magick->new;
$im->Set(size=>'400x300');   # creates the blanck image object with 400 x 300 geometry
$im->Set(Background=>'White'); #default background color is white
$im->draw(primitive=>'FillRectangle' , points=>'0,130 200,170' , method=> 'Floodfill' , pen=>'blue');  # create the rectangle
$im->draw(primitive=>'FillCircle' , points=>'110,150 65,150' , method=>'FloodFill' , pen=>'yellow'); #create the outer cirlce
$im->draw(primitive=>'FillCircle' , points=>'110,150 80,150' , method=>'FloodFill' , pen=>'black');  #create the inner cirlce
$im->draw(primitive=>'FillPolygon', points=>'200,150 400,75 325,150 400,225' method=>'Floodfill' , pen=>'pink'); #create the polygon
$im->write(filename=>'simplu.esp', compress=>'False');   #save file with the name 