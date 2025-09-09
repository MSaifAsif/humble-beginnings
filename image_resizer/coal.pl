#!usr/bin/perl

use Image::Magick;

use warnings;
use strict;

print "\n Coalesce the JPG files into a PDF file ";
print "\n -------------------------------------- ";
print "what Directory : ";
my $dir = <STDIN>; 	#input the directory
print "Output PDF file : ";
my $pdff = <STDIN>;	#input the pdf file name

chdir( $dir ) or die "\ncant chdir to $dir $!";
while( <*.jpg> ) {
  push @files, $_;	#save all files in array
}

my $pdf = Image::Magick->new();

foreach $file ( sort @files ) {
  my $img = Image::Magick->new();
  $rc = $img->Read( $file );
  warn $rc if $rc;
  push @$pdf, $img;
}

$pdf->[0]->Coalesce();	#join each file
$pdf->Write( $pdff);	#and write the result to the pdf file