
# Vehicle Logo Recognition

Image detection and recognition in general is one of the essential applications today. Image recognition has advanced and is now one of the most popular applications in the sector. For instance, self-driving cars, license plate recognition, etc. In order to detect automobile logos, this project pre-processes the image to make it look better before utilizing a specific technique to separate the logo from the background. The two functions that make up the entire project are the first one, which binarizes and cleans the image, and the second, which takes the image and other values as an argument and produces the segmented logo with its label![My First Board](https://user-images.githubusercontent.com/73842931/208689513-d4056dce-e91c-4074-a9e8-04a5158d6a8c.png)

### How it's Work 🤔
simply consists of 2 functions
- Pre-processing function 
The procedure uses the image to clean it up before attempting to extract the logo.

```matlab
function cleanimage = preprocess(img)
gimg = rgb2gray(img);
se = strel('disk',1);

gimg = imsubtract(imadd(gimg,imtophat(gimg,se)),imbothat(gimg,se));
bwimg = imbinarize(gimg);

imshow(img);
title("image");

imshow(gimg);
title("gray image");

figure,imshow(bwimg);
title("binary image");

cleanimage = bwimg;
end
```

- Logo detection function
The second function uses the parameters from the original image, the enhanced image, plus three more parameters that were discovered through trial and error for later-discussed objectives.

```matlab
function label = logodetection(img,bwimg,b,s,o)

se = strel('disk',1);

labeledImage = bwlabel(bwimg, 8);
measurements = regionprops(labeledImage, 'Area');
allAreas = [measurements.Area];
[biggestArea, indexOfBiggest] = sort(allAreas, 'descend');
new = bwimg;
%loop on all areas bigger than logo to remove them
for i=1:b
    biggestBlob = ismember(labeledImage, indexOfBiggest(i));
    biggestBlob = biggestBlob > 0;
    new = new - biggestBlob;
end

figure,imshow(new);
title("New image");

newer = new;
[smallestArea, indexOfSmallest] = sort(allAreas, 'ascend');
for i=1:s
    smallestBlob = ismember(labeledImage, indexOfSmallest(i));
    smallestBlob = smallestBlob > 0;
    newer = newer - smallestBlob;
end
newer = imfill(newer,'holes');
newer = imclearborder(newer);
newer = imdilate(newer,se);
newer = bwareaopen(newer, o);

imshow(newer);
title("Newer image");

[rows, columns] = find(newer);
row1 = min(rows);
row2 = max(rows);
col1 = min(columns);
col2 = max(columns);
croppedimg = newer(row1:row2, col1:col2);
    
title("cropped logo");
figure,imshow(croppedimg);

measurements = regionprops(newer, 'BoundingBox');
measurements = cell2mat(struct2cell(measurements));
finalimg = imcrop(img, measurements);
imshow(finalimg);

testcase = rgb2gray(finalimg);
testcase = imbinarize(testcase);

title("gray-scale");
figure,imshow(testcase);
title("Logo");

label = "";

end
```
### Steps 👣
**The first function  :** In order to execute all the actions on the image, the function `first` changes the image to its greyscale and then binarizes it. In order to employ it in some morphological operations performed on the image, a structuring element is defined `second`. This support is a disc with a diameter of 1. `Third`, the bottom-hat filtered version of the greyscale image is subtracted from the result of the addition after the top-hat filtered version of the greyscale image has been added.


**The Second function :** The purpose of the function is to arrange all of the image's related elements according to their geographic locations so that we can choose the logo from each of those areas. The function first labels every connected component before organizing them into an array by area. After that, it loops over the areas to get rid of any that are larger than the logo by sorting them in descending order. The third parameter, which is the number of regions larger than the logo, enters into play at this point. It is utilized in the for loop, which iterates across the regions array. After that, all of the little regions smaller than the logo are removed using the same for loop after sorting the array of areas in ascending order. The fourth parameter, which is the number of regions with smaller areas than the logo, enters the picture at this point. It's utilized in the for a loop. The logo is now segmented since it is purportedly the only component that is still there. To crop the logo from the original image, the algorithm measures the coordinates of this component.

