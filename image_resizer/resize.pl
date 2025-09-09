#!/user/local/bin/perl
use warnings;
use strict;

use Image::Magick;
my $img;

print "specify the resize geometry (axb) :"
$geo = <STDIN>;		#input geometry size

mkdir 'theLarge';

foreach (@ARGV){
	print "$_";
	$img = new Image::Magick;
	$img->Read($_);
	$img->Resize( geometry => $geo);  #resize to new geometry
	$img->write('./theLarge/$_'); 		#save the new image
	

}