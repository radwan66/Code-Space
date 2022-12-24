image1=imread("Case1-Front1.bmp");
image2=imread("Case2-Front2.jpg");
image3=imread("Case2-Rear1.jpg");
image4=imread("Case2-Rear2.jpg");
targetSize1 = [200 150];
r1 = centerCropWindow2d(size(image1),targetSize1);
J1 = imcrop(image1,r1);
rec1 = images.spatialref.Rectangle([53 80],[103 130])
l1 = imcrop(J1,rec1);
targetSize2 = [700 530];
r2 = centerCropWindow2d(size(image2),targetSize2);
J2 = imcrop(image2,r2);
rec2 = images.spatialref.Rectangle([385 490],[220 278])
l2 = imcrop(J2,rec2);
targetSize3 = [470 200];
r3 = centerCropWindow2d(size(image3),targetSize3);
J3 = imcrop(image3,r3);
rec3 = images.spatialref.Rectangle([0 35],[170 190])
l3 = imcrop(J3,rec3);
targetSize4 = [510 300];
r4 = centerCropWindow2d(size(image4),targetSize4);
J4 = imcrop(image4,r4);
rec4 = images.spatialref.Rectangle([90 130],[280 300])
l4 = imcrop(J4,rec4);
Logo1=imread("Opel.jpg");
Logo2=imread("Kia.jpg");
Logo3=imread("Hyundai1.jpg");
Logo4=imread("Hyundai2.jpg");
g1=rgb2gray(Logo1);
g2=rgb2gray(Logo2);
g3=rgb2gray(Logo3);
g4=rgb2gray(Logo4);
f1=rgb2gray(l1);
f2=rgb2gray(l2);
f3=rgb2gray(l3);
f4=rgb2gray(l4);
fftg1=fft2(double(g1));
g1features=abs(fftg1(:));
g1features=sort(g1features,'descend');
g1features=g1features(1:3);
fftg2=fft2(double(g2));
g2features=abs(fftg2(:));
g2features=sort(g2features,'descend');
g2features=g2features(1:3);
fftg3=fft2(double(g3));
g3features=abs(fftg3(:));
g3features=sort(g3features,'descend');
g3features=g3features(1:3);
fftg4=fft2(double(g4));
g4features=abs(fftg4(:));
g4features=sort(g4features,'descend');
g4features=g4features(1:3);
features=[g1features,g2features,g3features,g4features];

fftf1=fft2(double(f1));
imagefeatures1=abs(fftf1(:));
imagefeatures1=sort(imagefeatures1,'descend');
imagefeatures1=imagefeatures1(1:3);
fftf2=fft2(double(f2));
imagefeatures2=abs(fftf2(:));
imagefeatures2=sort(imagefeatures2,'descend');
imagefeatures2=imagefeatures2(1:3);
fftf3=fft2(double(f3));
imagefeatures3=abs(fftf3(:));
imagefeatures3=sort(imagefeatures3,'descend');
imagefeatures3=imagefeatures3(1:3);
fftf4=fft2(double(f4));
imagefeatures4=abs(fftf4(:));
imagefeatures4=sort(imagefeatures4,'descend');
imagefeatures4=imagefeatures4(1:3);
for i=1:4
nearesy(i)=sqrt((imagefeatures1(1)-features(1,i))^2+(imagefeatures1(2)-features(2,i))^2+(imagefeatures1(3)-features(3,i))^2);
end
nearesy
[MinResult,Index]=min(nearesy);
subplot(2,2,1),imshow(image1);
if Index==1
    logo1='Opel';
elseif Index==2
    logo1='Kia';
elseif Index==3 || Index==4
    logo1='Hyundai';
end
title(logo1);
subplot(2,2,2),imshow(l1);
for i=1:4
nearesy(i)=sqrt((imagefeatures2(1)-features(1,i))^2+(imagefeatures2(2)-features(2,i))^2+(imagefeatures2(3)-features(3,i))^2);
end
nearesy
[MinResult,index]=min(nearesy);
subplot(2,2,3),imshow(image2);
if index==1
    logo2='Opel';
elseif index==2
    logo2='Kia';
elseif index==3 || index==4
    logo2='Hyundai';
end
title(logo2);
subplot(2,2,4),imshow(l2);
for i=1:4
nearesy(i)=sqrt((imagefeatures3(1)-features(1,i))^2+(imagefeatures3(2)-features(2,i))^2+(imagefeatures3(3)-features(3,i))^2);
end
nearesy
[MinResult,index]=min(nearesy);
figure,subplot(2,2,1),imshow(image3);
if index==1
    logo3='Opel';
elseif index==2
    logo3='Kia';
elseif index==3 || index==4
    logo3='Hyundai';
end
title(logo3);
subplot(2,2,2),imshow(l3);
for i=1:4
nearesy(i)=sqrt((imagefeatures4(1)-features(1,i))^2+(imagefeatures4(2)-features(2,i))^2+(imagefeatures4(3)-features(3,i))^2);
end
nearesy;
[MinResult,index]=min(nearesy);
subplot(2,2,3),imshow(image4);
if index==1
    logo4='Opel';
elseif index==2
    logo4='Kia';
elseif index==3 || index==4
    logo4='Hyundai';
end
title(logo4);
subplot(2,2,4),imshow(l4);