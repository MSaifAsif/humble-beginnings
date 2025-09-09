#!/user/local/bin/perl
use warnings;
use strict;

use Math::Round;   # to use the round() method
use Image::Magick;
my $img;

mkdir 'thethumbs'; # create directory with the name of thethumbs

foreach (@ARGV){
	$img = new Image::Magick;
	$img->Read($_);		#read each image hash passes at command line
	($x_size, $y_size)=$img->Get('width','height');		#retrieve geometry of read image	
	($x, $y) = (100/$x_size, 150/$y_size);	#calculate the new values of x and y
	 if ( $x < $y)  {
     		$scale = $x;
		}
	 else {
		$scale = $y;
		}	# the scale variable now holds the scaleing factor
	my $geo;
	$geo = '$x_new'.'x'.'$y_new';
	($x_new,$y_new)=(round($x*$scale),round($y*$scale));   #calculate new geometry size
	$img->Resize( geometry => $geo);  #and resize the image according to the new geometry
	$img->Set(monochrome => 'True');  #color scheme set to black and white
	$img->write('./thethumbs/$_');    #save the image in the newly created directory
	

}