#!/usr/bin/perl
use warnings;
use strict;

foreach (@ARGV) {		#image names taken as arguments from command line
    print $_;


		use Image::Info qw(image_info dim);  		#Image/info.pm is used
                my $info = image_info($_); 			#Image::info() method called wiht the image hash passed as argument
		 if (my $error = $info->{error}) {
			     die "Can't parse image info: $error\n";  #if image parsing unsuccessfull
 				}

#----------- Reading Image File meta information and allocating variables to each attribute


my $colsp = $info->{ColorSpace};
my $comcn = $info->{ComponentsConfiguration};
my $dt = $info->{DateTime};
my $dtd = $info->{DateTimeDigitized};
my $dto = $info->{DateTimeOriginal};
my $eil = $info->{ExifImageLength};
my $eiw = $info->{ExifImageWidth};
my $ev = $info->{ExifVersion};
my $fs = $info->{FileSource};
my $fl = $info->{Flash};
my $fv = $info->{FlashPixVersion};
my $isr = $info->{ISOSpeedRatings};
my $id = $info->{ImageDescription};
my $dex = $info->{InteroperabilityIndex};
my $jt = $info->{JPEG_Type};
my $iv = $info->{InteroperabilityVersion};
my $lt = $info->{LightSource};
my $mk = $info->{Make};
my $mm = $info->{MeteringMode};
my $m = $info->{Model};
my $or = $info->{Orientation};
my $spp = $info->{SamplesPerPixel};
my $sw = $info->{Software};
my $po = $info->{YCbCrPositioning};
my $ct = $info->{color_type};
my $fe = $info->{file_ext};
my $mt = $info->{file_media_type};
my($w,$h) = dim($info);
my $res = $info->{resolution};

# ------ Printing each meta information of image

print "\n\n----------------------------$_-------------------------\n\n";
print ("ColorSpace => $colsp,\nComponentsConfiguration => $comcn, \nDateTime => $dt,\nDateTimeDigitized=> $dtd, \nDateTimeOriginal=> $dto ,\nExifImageLength => $eil, \nExifImageWidth => $eiw, \nExifVersion => $ev, \nFileSource => $fs, \nFlash => $fl,\nFlashPixVersion => $fv, \nISOSpeedRatings => $isr, \nImageDescription => $id, \nInteroperabilityIndex => $dex, \nJPEG_Type => $jt,\nInteroperabilityVersion => $iv, \nLightSource => $lt, \nMake => $mk ,\nMeteringMode => $mm, \nModel => $m, \nOrientation => $or,\nSamplesPerPixel => $spp, \nSoftware => $sw, \nYCbCrPositioning => $po,\ncolor_type => $ct , \nfile_ext => $fe ,\n file_media_type => $mt,\nheight => $h, \nresolution => $res , \nwidth => $w \n\n");

}
