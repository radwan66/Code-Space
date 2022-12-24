% originalimg = imread('Case1-Front1.bmp');
% cleanimg = preprocess(originalimg);
% label = logodetection(originalimg, cleanimg, 8, 297, 200);
% label;
% 
% originalimg = imread('Case2-Front2.jpg');
% cleanimg = preprocess(originalimg);
% label = logodetection(originalimg, cleanimg, 10, 5010, 3000);
% label;
% 
originalimg = imread('Case2-Rear1.jpg');
cleanimg = preprocess(originalimg);
label = logodetection(originalimg, cleanimg, 29, 340, 200);
label;
 
% originalimg = imread('Case2-Rear2.jpg');
% %turn image to grayscale
% gimg = rgb2gray(originalimg);
% 
% %Add the original image I to the top-hat filtered image, and then subtract the bottom-hat filtered image.
% gimg = imsubtract(imadd(gimg,imtophat(gimg,strel('disk',1))),imbothat(gimg,strel('disk',1)));
% 
% %Perform morphological opening on the image with se disk of size 10
% background = imopen(gimg,strel('disk',10));
% imshow(background)
% 
% %remove the background from the grayscale image
% I2 = gimg - background;
% imshow(I2)
% 
% %Adjust image intensity
% I3 = imadjust(I2);
% imshow(I3);
% 
% %Turn image to binary
% bw = imbinarize(I3);
% %Remove all connected components that have fewer than 50 pixels from binary image
% bw = bwareaopen(bw,50);
% %Perform morphological closing on the image with se disk of size 2
% bw = imclose(bw,strel('disk',2));
% %fill holes in binary image
% bwimg = imfill(bw,'holes');
% %Perform morphological eroding on the image with se disk of size 1
% cleanimg = imerode(bwimg,strel('disk',1));
% label = logodetection(originalimg, cleanimg, 15, 174, 400);
% label;


function cleanimage = preprocess(img)
%turn image to grayscale
gimg = rgb2gray(img);
%define structuring element of shape disk of size 1
se = strel('disk',1);
%Add the original image I to the top-hat filtered image, and then subtract the bottom-hat filtered image.
%top-hat filtering computes the morphological opening of the image (using imopen) and then subtracts the result from the original image.
%Bottom-hat filtering computes the morphological closing of the image and then subtracts the original image from the result.
%   the image is much enhanced from any external factors that affected the image
gimg = imsubtract(imadd(gimg,imtophat(gimg,se)),imbothat(gimg,se));
%Turn image to binary
bwimg = imbinarize(gimg);

imshow(img);
title("image");

imshow(gimg);
title("gray image");

figure,imshow(bwimg);
title("binary image");

cleanimage = bwimg;
end
% b -> value to loop on all areas bigger than logo to remove them
% s -> value to sort areas in ascending order and retreiving each area with its index
% o -> value to Remove all connected components that have fewer than o pixels(fifth parameter) from binary image
function label = logodetection(img,bwimg,b,s,o)

%define structuring element of shape disk of size 1
se = strel('disk',1);

%Label disconnected components in binary image

labeledImage = bwlabel(bwimg, 8);
%Measure properties of image regions using the labels retreived according to the area
measurements = regionprops(labeledImage, 'Area');
%put the areas in an array
allAreas = [measurements.Area];
%sort areas in descending order and retreiving each area with its index
[biggestArea, indexOfBiggest] = sort(allAreas, 'descend');
new = bwimg;
%loop on all areas bigger than logo to remove them
for i=1:b
    %returns an array containing logical 1 (true) where the data in labeledImage is found in indexOfBiggest(measurments). Elsewhere, the array contains logical 0 (false).
    biggestBlob = ismember(labeledImage, indexOfBiggest(i));
    %returns the image with threshhold anything bigger than 0
    biggestBlob = biggestBlob > 0;
    %remove the biggest area from the image
    new = new - biggestBlob;
end

figure,imshow(new);
title("New image");

newer = new;
%sort areas in ascending order and retreiving each area with its index
[smallestArea, indexOfSmallest] = sort(allAreas, 'ascend');
%loop on all areas smaller than logo to remove them
for i=1:s
    %returns an array containing logical 1 (true) where the data in labeledImage is found in indexOfBiggest(measurments). Elsewhere, the array contains logical 0 (false).
    smallestBlob = ismember(labeledImage, indexOfSmallest(i));
    %returns the image with threshhold anything bigger than 0
    smallestBlob = smallestBlob > 0;
    %remove the biggest area from the image
    newer = newer - smallestBlob;
end
%fill holes in logo if found to keep logo intact
newer = imfill(newer,'holes');
%Suppress light structures connected to image border
newer = imclearborder(newer);
%Perform morphological dialating on the image with se disk of size 1
newer = imdilate(newer,se);
%Remove all connected components that have fewer than o pixels(fifth parameter) from binary image
newer = bwareaopen(newer, o);

imshow(newer);
title("Newer image");

%Crop logo
%retreive rows and columns of the final image
[rows, columns] = find(newer);
%get maximum and minimum of rows
row1 = min(rows);
row2 = max(rows);
%get maximum and minimum of columns
col1 = min(columns);
col2 = max(columns);
%crop the image from from minimum to maximum of rows and columns
croppedimg = newer(row1:row2, col1:col2);
    
title("cropped logo");
figure,imshow(croppedimg);
% end croping

%Measure properties of image regions where bounding box is gives position and size of the smallest box containing the region
measurements = regionprops(newer, 'BoundingBox');
%convert what is retreived from measurments from struct into a matrix
measurements = cell2mat(struct2cell(measurements));
%crop the image according to the coordinates retreived
finalimg = imcrop(img, measurements);

imshow(finalimg);


%Read the data set, convert it to its grayscale and binay form
opel = imread('opell.jpg');
gopel = rgb2gray(opel);
bwopel = imbinarize(gopel);

kia = imread('kiaa.jpeg');
gkia = rgb2gray(kia);
bwkia = imbinarize(gkia);

hyundai = imread('hyundaii.jpg');
ghyundai = rgb2gray(hyundai);
bwhyundai = imbinarize(ghyundai);
bwhyundai = ~bwhyundai;
bwhyundai = imclose(bwhyundai,strel('disk', 1));

%Aquire first 10 features using Fourier Transform of each image of the dataset
fftD1=fft2(double(bwopel));
d1features = abs(fftD1(:));
d1features = sort(d1features, 'descend');
d1features = d1features(1:10);

fftD2=fft2(double(bwkia));
d2features = abs(fftD2(:));
d2features = sort(d2features, 'descend');
d2features = d2features(1:10);

fftD3=fft2(double(bwhyundai));
d3features = abs(fftD3(:));
d3features = sort(d3features, 'descend');
d3features = d3features(1:10);
%Convert the logo into grayscale and get binary form
testcase = rgb2gray(finalimg);
testcase = imbinarize(testcase);


%put features of images of the dataset in an array
%           opel        kia         hyundai     
features = [d1features, d2features, d3features];

%Aquire first 10 features using Fourier Transform of logo
image = testcase;
fftI = fft2(double(image));
imagefeatures=abs(fftI(:));
imagefeatures=sort(imagefeatures,'descend');
imagefeatures=imagefeatures(1:10);


%loop on the array of features to find the closest image similar to the logo
for i=1:3
nearest(i)=sqrt((imagefeatures(1)-features(1,i))^2+(imagefeatures(2)-features(2,i))^2+(imagefeatures(3)-features(3,i))^2);
end

[MinResult,Index]=min(nearest);

label = "";

%label each index
if Index == 1
    label = "Opel";
end

if Index == 2
    label = "KIA";
end

if Index == 3
    label = "Huyndai";
end

imshow(testcase);
title(label);
% %Convert the logo into grayscale and get binary form
% testcase = rgb2gray(finalimg);
% testcase = imbinarize(testcase);
% 
% title("gray-scale");
% figure,imshow(testcase);
% title("Logo");
% 
% label = "";

end