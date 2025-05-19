# Carteav Polygon Mask Project

This project fills non-drivable areas in an aerial image using polygon data.

## How to Run

1. Install dependencies:

   ```bash
   pip install -r requirements.txt
   ```

2. Run the main script:

   ```bash
   python main.py
   ```

3. The output image will be saved to:
   ```
   Resources/Photos/output.jpg
   ```

### Requirements

- Python 3.7+
- OpenCV
- pyyaml

---

## Folder Structure

```
Carteav-Project/
├── Resources/
│   ├── Data/
│   │   ├── haleom_raster.txt      # Top-left and bottom-right coordinates in meters
│   │   └── map_2d.yaml            # Polygon data
│   ├── Photos/                    # Input/output images
├── main.py                        # Main script
├── README.md                      # This file
└── requirements.txt               # Required Python packages
```
